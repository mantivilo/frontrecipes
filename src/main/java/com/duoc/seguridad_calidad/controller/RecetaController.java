package com.duoc.seguridad_calidad.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import com.duoc.seguridad_calidad.model.Receta;

@Controller
public class RecetaController {

     private List<Receta> recetas = List.of(
        new Receta("Tacos", "Mexicana", List.of("Tortillas", "Carne", "Salsa"), "México", "Fácil", "Instrucciones de preparación", 30, "/images/tacos.jpg"),
        new Receta("Pizza", "Italiana", List.of("Masa", "Queso", "Salsa de Tomate"), "Italia", "Media", "Instrucciones de preparación", 45, "/images/pizza.jpg")
    );

    @GetMapping("/buscar")
public String buscar(@RequestParam(value = "nombre", required = false) String nombre,
                     @RequestParam(value = "tipoCocina", required = false) String tipoCocina,
                     Model model) {
    System.out.println("Nombre: " + nombre);
    System.out.println("Tipo de Cocina: " + tipoCocina);

    // Filtrar recetas según los parámetros
    List<Receta> recetasFiltradas = recetas.stream()
        .filter(receta -> (nombre == null || nombre.isEmpty() || receta.getNombre().toLowerCase().contains(nombre.toLowerCase())) &&
                          (tipoCocina == null || tipoCocina.isEmpty() || receta.getTipoCocina().equalsIgnoreCase(tipoCocina)))
        .toList();

    

    model.addAttribute("recetas", recetasFiltradas);
    return "buscarRecetas"; // Asegúrate de que este sea el nombre correcto de tu vista
}
    
}
