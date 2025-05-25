package com.mottu.fleet.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateZonaDTO {

    @NotNull(message = "O ID da zona é obrigatório.")
    private Long id;

    @NotBlank(message = "O nome da zona é obrigatório.")
    private String nome;

    @NotBlank(message = "O tipo da zona é obrigatório.")
    private String tipo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
