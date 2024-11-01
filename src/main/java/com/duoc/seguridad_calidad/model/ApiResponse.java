package com.duoc.seguridad_calidad.model;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class ApiResponse {

    private String message;



    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
