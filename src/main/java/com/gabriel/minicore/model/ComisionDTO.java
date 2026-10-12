package com.gabriel.minicore.model;

import java.math.BigDecimal;

/** Resultado del calculo por vendedor (no es entidad, solo para la vista). */
public record ComisionDTO(
        String vendedor,
        int numeroVentas,
        BigDecimal totalVentas,
        BigDecimal porcentaje,
        BigDecimal comision
) {}
