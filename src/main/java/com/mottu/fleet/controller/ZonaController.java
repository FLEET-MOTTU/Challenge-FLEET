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

@RestController
@RequestMapping("/api/zonas")
public class ZonaController {

    private final ZonaService zonaService;

    public ZonaController(ZonaService zonaService) {
        this.zonaService = zonaService;
    }

    @PostMapping
    public ResponseEntity<ZonaDTO> criar(@RequestBody @Valid CreateZonaDTO dto) {
        ZonaDTO zonaCriada = zonaService.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(zonaCriada);
    }

    @GetMapping
    public Page<ZonaDTO> listar(Pageable pageable) {
        return zonaService.listar(pageable);
    }
}
