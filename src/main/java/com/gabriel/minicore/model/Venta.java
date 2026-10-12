package com.gabriel.minicore.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "venta")
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "vendedor_id")
    private Vendedor vendedor;

    @Column(nullable = false)
    private LocalDate fecha;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal monto;

    public Venta() {}

    public Venta(Vendedor vendedor, LocalDate fecha, BigDecimal monto) {
        this.vendedor = vendedor;
        this.fecha = fecha;
        this.monto = monto;
    }

    public Long getId() { return id; }
    public Vendedor getVendedor() { return vendedor; }
    public LocalDate getFecha() { return fecha; }
    public BigDecimal getMonto() { return monto; }
}
