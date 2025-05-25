package com.mottu.fleet.repository;

import com.mottu.fleet.model.Zona;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ZonaRepository extends JpaRepository<Zona, Long> {
    Optional<Zona> findByNome(String nome);
}
