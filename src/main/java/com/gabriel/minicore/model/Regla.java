package com.gabriel.minicore.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

/**
 * Regla de comision: si el total vendido en el rango es >= montoMinimo,
 * se aplica el porcentaje. Se usa la regla de mayor montoMinimo que cumpla.
 */
@Entity
@Table(name = "regla")
public class Regla {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal montoMinimo;

    @Column(nullable = false, precision = 5, scale = 2)
    private BigDecimal porcentaje; // ej: 6.00 = 6%

    public Regla() {}

    public Regla(BigDecimal montoMinimo, BigDecimal porcentaje) {
        this.montoMinimo = montoMinimo;
        this.porcentaje = porcentaje;
    }

    public Long getId() { return id; }
    public BigDecimal getMontoMinimo() { return montoMinimo; }
    public BigDecimal getPorcentaje() { return porcentaje; }
}
