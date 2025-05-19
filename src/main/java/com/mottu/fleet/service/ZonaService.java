package com.mottu.fleet.service;

import com.mottu.fleet.dto.CreateZonaDTO;
import com.mottu.fleet.dto.ZonaDTO;
import com.mottu.fleet.model.Zona;
import com.mottu.fleet.repository.ZonaRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ZonaService {

    private final ZonaRepository zonaRepository;

    public ZonaService(ZonaRepository zonaRepository) {
        this.zonaRepository = zonaRepository;
    }

    public ZonaDTO criar(CreateZonaDTO dto) {
        Zona zona = new Zona();
        zona.setNome(dto.getNome());
        zona.setTipo(dto.getTipo());

        zonaRepository.save(zona);
        return toDTO(zona);
    }

    @Cacheable("zonas")
    public Page<ZonaDTO> listar(Pageable pageable) {
        return zonaRepository.findAll(pageable).map(this::toDTO);
    }

    public ZonaDTO toDTO(Zona zona) {
        ZonaDTO dto = new ZonaDTO();
        dto.setId(zona.getId());
        dto.setNome(zona.getNome());
        dto.setTipo(zona.getTipo());
        return dto;
    }
}
