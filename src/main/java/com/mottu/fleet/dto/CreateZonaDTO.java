package com.mottu.fleet.dto;

import jakarta.validation.constraints.NotBlank;

public class CreateZonaDTO {

    @NotBlank(message = "O nome da zona é obrigatório.")
    private String nome;

    @NotBlank(message = "O tipo da zona é obrigatório.")
    private String tipo;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
