package com.duoc.recetasfrontend.model;
import lombok.Data;
import java.util.List;

@Data
public class Receta {

    private String nombre;
    private String tipoCocina;
    private String paisOrigen;
    private String dificultad;
    private String ingredientes;
    private String instrucciones;
    private int tiempoCoccion;
    private String fotografiaUrl;

}

