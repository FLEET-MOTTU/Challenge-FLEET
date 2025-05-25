package com.mottu.fleet.dto;

import jakarta.validation.constraints.NotBlank;

public class MagicLinkRequestDTO {

    @NotBlank(message = "Telefone é obrigatório")
    private String telefone;

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
