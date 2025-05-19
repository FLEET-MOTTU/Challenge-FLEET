package com.mottu.fleet.controller;

import com.mottu.fleet.dto.CreateMotoDTO;
import com.mottu.fleet.dto.MotoDTO;
import com.mottu.fleet.service.MotoService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/motos")
public class MotoController {

    private final MotoService motoService;

    public MotoController(MotoService motoService) {
        this.motoService = motoService;
    }

    @PostMapping
    public ResponseEntity<MotoDTO> criar(@RequestBody @Valid CreateMotoDTO dto) {
        MotoDTO motoCriada = motoService.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(motoCriada);
    }

    @GetMapping
    public Page<MotoDTO> listarPorStatus(@RequestParam String status,
                                         @PageableDefault(size = 5, sort = "placa") Pageable pageable) {
        return motoService.listarPorStatus(status, pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MotoDTO> buscarPorId(@PathVariable Long id) {
        return motoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
