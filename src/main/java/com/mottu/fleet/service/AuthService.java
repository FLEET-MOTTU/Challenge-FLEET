package com.mottu.fleet.service;

import com.mottu.fleet.dto.LoginDTO;
import com.mottu.fleet.dto.MagicLinkRequestDTO;
import com.mottu.fleet.dto.TokenValidationDTO;
import com.mottu.fleet.model.Funcionario;
import com.mottu.fleet.model.TokenDeAcesso;
import com.mottu.fleet.repository.FuncionarioRepository;
import com.mottu.fleet.repository.TokenDeAcessoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthService {

    private final FuncionarioRepository funcionarioRepository;
    private final TokenDeAcessoRepository tokenRepository;

    public AuthService(FuncionarioRepository funcionarioRepository,
                       TokenDeAcessoRepository tokenRepository) {
        this.funcionarioRepository = funcionarioRepository;
        this.tokenRepository = tokenRepository;
    }

    public String gerarLinkMagico(MagicLinkRequestDTO dto) {
        Funcionario funcionario = funcionarioRepository.findByTelefone(dto.getTelefone())
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado"));

        String token = UUID.randomUUID().toString();
        TokenDeAcesso acesso = new TokenDeAcesso();
        acesso.setToken(token);
        acesso.setFuncionario(funcionario);
        acesso.setCriadoEm(LocalDateTime.now());
        acesso.setExpiraEm(LocalDateTime.now().plusHours(24));
        acesso.setUsado(false);

        tokenRepository.save(acesso);

        return "https://fleetapp.com/auth/magic-login?token=" + token;
    }

    public String validarToken(TokenValidationDTO dto) {
        TokenDeAcesso token = tokenRepository.findByToken(dto.getToken())
                .orElseThrow(() -> new RuntimeException("Token inválido"));

        if (token.isUsado()) {
            throw new RuntimeException("Token já foi utilizado");
        }

        if (token.getExpiraEm().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Token expirado");
        }

        token.setUsado(true);
        token.setDispositivoRegistrado(dto.getDispositivo());
        token.getFuncionario().setUltimoLogin(LocalDateTime.now());

        funcionarioRepository.save(token.getFuncionario());
        tokenRepository.save(token);

        return "Token válido e login realizado";
    }

    public Optional<Funcionario> autenticarPorLoginESenha(LoginDTO dto) {
        return funcionarioRepository.findByLoginAndSenha(dto.getLogin(), dto.getSenha());
    }
}
