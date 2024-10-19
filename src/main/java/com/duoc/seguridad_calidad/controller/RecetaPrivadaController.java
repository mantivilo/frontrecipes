package com.duoc.seguridad_calidad.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.duoc.seguridad_calidad.model.Receta;


@Controller
@RequestMapping("/recetas")
public class RecetaPrivadaController {

    private List<Receta> recetas = List.of(
        new Receta("Tacos", "Mexicana", List.of("Tortillas", "Carne", "Salsa"), "México", "Fácil", "Instrucciones de preparación", 30, "/images/tacos.jpg"),
        new Receta("Pizza", "Italiana", List.of("Masa", "Queso", "Salsa de Tomate"), "Italia", "Media", "Instrucciones de preparación", 45, "/images/pizza.jpg")
    );

    @GetMapping("/detalles/{nombre}")
    @PreAuthorize("isAuthenticated()")
    public String verDetalles(@PathVariable String nombre, Model model) {
        Receta receta = recetas.stream()
            .filter(r -> r.getNombre().equalsIgnoreCase(nombre))
            .findFirst()
            .orElse(null);
        model.addAttribute("receta", receta);
        return "detalleReceta";
    }
}
