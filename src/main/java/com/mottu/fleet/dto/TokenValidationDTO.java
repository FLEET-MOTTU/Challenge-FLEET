package com.mottu.fleet.dto;

import jakarta.validation.constraints.NotBlank;

public class TokenValidationDTO {

    @NotBlank(message = "Token é obrigatório")
    private String token;

    private String dispositivo;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getDispositivo() {
        return dispositivo;
    }

    public void setDispositivo(String dispositivo) {
        this.dispositivo = dispositivo;
    }
}
