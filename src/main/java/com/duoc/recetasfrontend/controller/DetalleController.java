package com.duoc.recetasfrontend.controller;


import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import com.duoc.recetasfrontend.model.Receta;
import com.duoc.recetasfrontend.model.TokenStore;

@Controller
public class DetalleController {

    String url = "http://localhost:8080";

    private TokenStore tokenStore;

    public DetalleController(TokenStore tokenStore) {
        super();
        this.tokenStore = tokenStore;
    }

    @GetMapping("/recetas/{id}/detalle")
    public String getRecetaDetalle(@PathVariable Long id, Model model) {

        final var restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();

        headers.set("Authorization", "Bearer " + this.tokenStore.getToken());  //Adding "Bearer "
        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        // Build the URL for the detail recipe ID.
        String detalleUrl = url + "/private/recetas/" + id + "/detalle";

        try {
            //GET call to backend and parse the response as an object (tipo receta)
            ResponseEntity<Receta> response = restTemplate.exchange(detalleUrl, HttpMethod.GET, entity, Receta.class);

            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                // pass the detail recipe to the view
                model.addAttribute("detalles", response.getBody());
            } else {
                model.addAttribute("error", "No se pudo obtener los detalles de la recera.");
            }
        } catch (Exception e) {
            model.addAttribute("error", "Error al obtener los detalles de la receta: " + e.getMessage());
        }

        return "detailrecipe";
    }



}
