package com.duoc.seguridad_calidad.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import com.duoc.seguridad_calidad.model.*;
import com.duoc.seguridad_calidad.model.Receta;


@Controller
public class HomeController {

    private List<Receta> recetas = List.of(
        new Receta("Tacos", "Mexicana", List.of("Tortillas", "Carne", "Salsa"), "México", "Fácil", "Instrucciones de preparación", 30, "/images/tacos.jpg"),
        new Receta("Pizza", "Italiana", List.of("Masa", "Queso", "Salsa de Tomate"), "Italia", "Media", "Instrucciones de preparación", 45, "/images/pizza.jpg")
    );

    private List<Banner> banners = List.of(
        new Banner("Compañía A", "/images/banner1.jpg", "https://companiaA.com"),
        new Banner("Compañía B", "/images/banner2.jpg", "https://companiaB.com")
    );

    @GetMapping("/home")
    public String home(@RequestParam (name = "name", required = false, defaultValue = "Seguridad y calidad en el Desarrollo")
    String name, Model model) {
        model.addAttribute("name", name);
        model.addAttribute("recetas", recetas);
        model.addAttribute("banners", banners);
        return "home";
    }

    @GetMapping("/")
    public String root(@RequestParam (name = "name", required = false, defaultValue = "Seguridad y calidad en el Desarrollo")
    String name, Model model) {
        model.addAttribute("name", name);
        model.addAttribute("recetas", recetas);
        model.addAttribute("banners", banners);
        return "home";
    }
    
    
    
}
