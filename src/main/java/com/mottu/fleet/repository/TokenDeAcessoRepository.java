package com.mottu.fleet.repository;

import com.mottu.fleet.model.TokenDeAcesso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenDeAcessoRepository extends JpaRepository<TokenDeAcesso, String> {
    Optional<TokenDeAcesso> findByToken(String token);
}
