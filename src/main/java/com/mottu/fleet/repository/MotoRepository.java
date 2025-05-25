package com.mottu.fleet.repository;

import com.mottu.fleet.model.Moto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MotoRepository extends JpaRepository<Moto, Long> {
    Page<Moto> findByStatus(String status, Pageable pageable);
}
