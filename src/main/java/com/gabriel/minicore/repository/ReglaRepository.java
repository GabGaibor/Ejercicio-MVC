package com.gabriel.minicore.repository;

import com.gabriel.minicore.model.Regla;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReglaRepository extends JpaRepository<Regla, Long> {

    List<Regla> findAllByOrderByMontoMinimoDesc();
}
