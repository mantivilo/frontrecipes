package com.duoc.recetasfrontend.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.duoc.recetasfrontend.model.*;


@Controller
public class HomeController {

    String url = "http://localhost:8080";


    @GetMapping("/home")
    public String home(Model model) {
        final var restTemplate = new RestTemplate();

        // Realiza la solicitud GET al backend para obtener el mapa de respuesta
        ResponseEntity<Map> response = restTemplate.exchange(url.concat("/public/home"), HttpMethod.GET, null, Map.class);

        // Extrae los datos del mapa
        Map<String, Object> responseBody = response.getBody();

        if (responseBody != null) {
            model.addAttribute("recetasRecientes", responseBody.get("recetasRecientes"));
            model.addAttribute("recetasPopulares", responseBody.get("recetasPopulares"));
            model.addAttribute("banners", responseBody.get("banners"));
        }

        return "home";
    }

    @GetMapping("/buscar")
    public String buscarRecetas(
            @RequestParam(value = "nombre", required = false) String nombre,
            @RequestParam(value = "tipoCocina", required = false) String tipoCocina,
            @RequestParam(value = "paisOrigen", required = false) String paisOrigen,
            @RequestParam(value = "dificultad", required = false) String dificultad,
            Model model) {

        // Construye la URL de búsqueda con los parámetros
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url + "/public/buscar")
                .queryParam("nombre", nombre)
                .queryParam("tipoCocina", tipoCocina)
                .queryParam("paisOrigen", paisOrigen)
                .queryParam("dificultad", dificultad);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List> response = restTemplate.getForEntity(builder.toUriString(), List.class);

        // Agrega los resultados al modelo para mostrar en la vista
        model.addAttribute("resultados", response.getBody());

        return "buscarRecetas";
    }
    
    
    
}
