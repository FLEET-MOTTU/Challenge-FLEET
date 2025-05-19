package com.mottu.fleet.service;

import com.mottu.fleet.dto.CreateMotoDTO;
import com.mottu.fleet.dto.MotoDTO;
import com.mottu.fleet.model.Moto;
import com.mottu.fleet.model.Zona;
import com.mottu.fleet.repository.MotoRepository;
import com.mottu.fleet.repository.ZonaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MotoService {

    private final MotoRepository motoRepository;
    private final ZonaRepository zonaRepository;

    public MotoService(MotoRepository motoRepository, ZonaRepository zonaRepository) {
        this.motoRepository = motoRepository;
        this.zonaRepository = zonaRepository;
    }

    public MotoDTO criar(CreateMotoDTO dto) {
        Moto moto = new Moto();
        moto.setPlaca(dto.getPlaca());
        moto.setStatus(dto.getStatus());
        moto.setTagBle(dto.getTagBle());

        Zona zona = zonaRepository.findById(dto.getZonaId())
                .orElseThrow(() -> new IllegalArgumentException("Zona não encontrada"));

        moto.setZona(zona);
        motoRepository.save(moto);

        return toDTO(moto);
    }

    public Page<MotoDTO> listarPorStatus(String status, Pageable pageable) {
        return motoRepository.findByStatus(status, pageable).map(this::toDTO);
    }

    public Optional<MotoDTO> buscarPorId(Long id) {
        return motoRepository.findById(id).map(this::toDTO);
    }

    public MotoDTO toDTO(Moto moto) {
        MotoDTO dto = new MotoDTO();
        dto.setId(moto.getId());
        dto.setPlaca(moto.getPlaca());
        dto.setStatus(moto.getStatus());
        dto.setTagBle(moto.getTagBle());
        dto.setNomeZona(moto.getZona().getNome());
        return dto;
    }
}
