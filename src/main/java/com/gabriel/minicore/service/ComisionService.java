package com.gabriel.minicore.service;

import com.gabriel.minicore.model.ComisionDTO;
import com.gabriel.minicore.model.Regla;
import com.gabriel.minicore.model.Vendedor;
import com.gabriel.minicore.model.Venta;
import com.gabriel.minicore.repository.ReglaRepository;
import com.gabriel.minicore.repository.VendedorRepository;
import com.gabriel.minicore.repository.VentaRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * MINI-CORE: calcula la comision de cada vendedor en un rango de fechas.
 *
 * 1. Filtra las ventas entre fechaInicio y fechaFin.
 * 2. Agrupa y suma las ventas por vendedor.
 * 3. Busca la regla de mayor monto minimo que el total alcance.
 * 4. comision = total * porcentaje / 100
 */
@Service
public class ComisionService {

    private final VendedorRepository vendedorRepo;
    private final VentaRepository ventaRepo;
    private final ReglaRepository reglaRepo;

    public ComisionService(VendedorRepository vendedorRepo,
                           VentaRepository ventaRepo,
                           ReglaRepository reglaRepo) {
        this.vendedorRepo = vendedorRepo;
        this.ventaRepo = ventaRepo;
        this.reglaRepo = reglaRepo;
    }

    public List<ComisionDTO> calcular(LocalDate inicio, LocalDate fin) {
        // 1. Filtrar ventas por rango
        List<Venta> ventas = ventaRepo.findByFechaBetween(inicio, fin);

        // 2. Agrupar por vendedor
        Map<Long, List<Venta>> ventasPorVendedor = ventas.stream()
                .collect(Collectors.groupingBy(v -> v.getVendedor().getId()));

        // Reglas ordenadas de mayor a menor monto minimo
        List<Regla> reglas = reglaRepo.findAllByOrderByMontoMinimoDesc();

        List<ComisionDTO> resultado = new ArrayList<>();
        for (Vendedor vendedor : vendedorRepo.findAll()) {
            List<Venta> suyas = ventasPorVendedor.getOrDefault(vendedor.getId(), List.of());

            BigDecimal total = suyas.stream()
                    .map(Venta::getMonto)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            // 3. Regla aplicable
            BigDecimal porcentaje = reglas.stream()
                    .filter(r -> total.compareTo(r.getMontoMinimo()) >= 0)
                    .map(Regla::getPorcentaje)
                    .findFirst()
                    .orElse(BigDecimal.ZERO);

            // 4. Comision
            BigDecimal comision = total.multiply(porcentaje)
                    .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);

            resultado.add(new ComisionDTO(vendedor.getNombre(), suyas.size(),
                    total.setScale(2, RoundingMode.HALF_UP), porcentaje, comision));
        }

        resultado.sort(Comparator.comparing(ComisionDTO::comision).reversed());
        return resultado;
    }

    public List<Regla> reglas() {
        return reglaRepo.findAllByOrderByMontoMinimoDesc();
    }
}
