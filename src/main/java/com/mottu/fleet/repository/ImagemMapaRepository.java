package com.mottu.fleet.repository;

import com.mottu.fleet.model.ImagemMapa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImagemMapaRepository extends JpaRepository<ImagemMapa, Long> {
    Optional<ImagemMapa> findTopByTipoOrderByDataUploadDesc(String tipo);
}
