package com.mottu.fleet.controller;

import com.mottu.fleet.dto.LoginDTO;
import com.mottu.fleet.dto.MagicLinkRequestDTO;
import com.mottu.fleet.dto.TokenValidationDTO;
import com.mottu.fleet.model.Funcionario;
import com.mottu.fleet.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginDTO dto) {
        Optional<Funcionario> funcionarioOpt = authService.autenticarPorLoginESenha(dto);

        if (funcionarioOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login ou senha inválidos");
        }

        Funcionario funcionario = funcionarioOpt.get();
        Map<String, Object> response = new HashMap<>();
        response.put("id", funcionario.getId());
        response.put("nome", funcionario.getNome());
        response.put("tipo", funcionario.isAdm() ? "ADMIN" : "FUNCIONARIO");

        return ResponseEntity.ok(response);
    }
}
