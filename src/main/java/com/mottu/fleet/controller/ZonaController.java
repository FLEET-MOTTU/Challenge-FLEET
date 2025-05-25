package com.mottu.fleet.controller;

import com.mottu.fleet.dto.CreateZonaDTO;
import com.mottu.fleet.dto.ZonaDTO;
import com.mottu.fleet.service.ZonaService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/zonas")
public class ZonaController {

    private final ZonaService zonaService;

    public ZonaController(ZonaService zonaService) {
        this.zonaService = zonaService;
    }

    // POST - Criação com validação de ID obrigatório
    @PostMapping
    public ResponseEntity<ZonaDTO> criar(@RequestBody @Valid CreateZonaDTO dto) {
        if (dto.getId() == null) {
            return ResponseEntity.badRequest().build();
        }
        ZonaDTO zonaCriada = zonaService.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(zonaCriada);
    }

    // GET - Listar com paginação
    @GetMapping
    public Page<ZonaDTO> listar(Pageable pageable) {
        return zonaService.listar(pageable);
    }

    // GET - Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<ZonaDTO> buscarPorId(@PathVariable Long id) {
        Optional<ZonaDTO> zonaDTO = zonaService.buscarPorId(id);
        return zonaDTO.map(ResponseEntity::ok)
                      .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
