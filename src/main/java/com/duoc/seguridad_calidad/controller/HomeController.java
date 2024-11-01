package com.duoc.seguridad_calidad.controller;

import java.util.List;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import com.duoc.seguridad_calidad.model.*;
import com.duoc.seguridad_calidad.model.Receta;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;


@Controller
public class HomeController {

    String url = "http://localhost:8080/public/home";


    private List<Banner> banners = List.of(
        new Banner("Compañía A", "/images/banner1.jpg", "https://companiaA.com"),
        new Banner("Compañía B", "/images/banner2.jpg", "https://companiaB.com")
    );

    @GetMapping("/home")
    public String home(@RequestParam (name = "name", required = false, defaultValue = "Seguridad y calidad en el Desarrollo")
    String name, Model model) {
        final var restTemplate = new RestTemplate();

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("name", name);
        ResponseEntity response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, null, String.class);

        System.out.println("Response: " + response);

        model.addAttribute("name", response.getBody());


        return "home";
    }

//    @GetMapping("/")
//    public String root(@RequestParam (name = "name", required = false, defaultValue = "Seguridad y calidad en el Desarrollo")
//    String name, Model model) {
//        model.addAttribute("name", name);
//        model.addAttribute("recetas", recetas);
//        model.addAttribute("banners", banners);
//        return "home";
//    }
    
    
    
}
