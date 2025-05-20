package com.mottu.fleet.controller;

import com.mottu.fleet.dto.MagicLinkRequestDTO;
import com.mottu.fleet.dto.TokenValidationDTO;
import com.mottu.fleet.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/magic-link")
    public ResponseEntity<String> gerarLinkMagico(@RequestBody @Valid MagicLinkRequestDTO dto) {
        String link = authService.gerarLinkMagico(dto);
        return ResponseEntity.ok("Link gerado com sucesso: " + link);
    }

    @PostMapping("/validar-token")
    public ResponseEntity<String> validarToken(@RequestBody @Valid TokenValidationDTO dto) {
        String resultado = authService.validarToken(dto);
        return ResponseEntity.ok(resultado);
    }
}
