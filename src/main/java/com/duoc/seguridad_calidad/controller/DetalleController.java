package com.duoc.seguridad_calidad.controller;


import com.duoc.seguridad_calidad.model.Receta;
import com.duoc.seguridad_calidad.model.TokenStore;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

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

        headers.set("Authorization", "Bearer " + this.tokenStore.getToken());  // Agregar prefijo "Bearer "
        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        // Construir la URL para la receta específica
        String detalleUrl = url + "/private/recetas/" + id + "/detalle";

        try {
            // Hacer la solicitud GET al backend y parsear la respuesta como un objeto de tipo Receta
            ResponseEntity<Receta> response = restTemplate.exchange(detalleUrl, HttpMethod.GET, entity, Receta.class);

            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                // Pasar los detalles de la receta a la vista
                model.addAttribute("detalles", response.getBody());
            } else {
                model.addAttribute("error", "No se pudieron obtener los detalles de la receta.");
            }
        } catch (Exception e) {
            model.addAttribute("error", "Ocurrió un error al obtener los detalles de la receta: " + e.getMessage());
        }

        return "detalleReceta";
    }



}
