package com.gabriel.minicore.controller;

import com.gabriel.minicore.model.ComisionDTO;
import com.gabriel.minicore.service.ComisionService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Controller
public class ComisionController {

    private final ComisionService service;

    public ComisionController(ComisionService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String index(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fin,
            Model model) {

        model.addAttribute("reglas", service.reglas());
        model.addAttribute("inicio", inicio);
        model.addAttribute("fin", fin);

        if (inicio != null && fin != null) {
            if (inicio.isAfter(fin)) {
                model.addAttribute("error", "La fecha de inicio no puede ser mayor a la fecha fin.");
            } else {
                List<ComisionDTO> resultados = service.calcular(inicio, fin);
                BigDecimal totalComisiones = resultados.stream()
                        .map(ComisionDTO::comision)
                        .reduce(BigDecimal.ZERO, BigDecimal::add);
                model.addAttribute("resultados", resultados);
                model.addAttribute("totalComisiones", totalComisiones);
            }
        }
        return "index";
    }
}
