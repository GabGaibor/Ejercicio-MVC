package com.gabriel.minicore.repository;

import com.gabriel.minicore.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface VentaRepository extends JpaRepository<Venta, Long> {

    /** Ventas cuya fecha esta entre inicio y fin (inclusive). */
    List<Venta> findByFechaBetween(LocalDate inicio, LocalDate fin);
}
