package com.mottu.fleet.dto;

import jakarta.validation.constraints.NotBlank;

public class CreateFuncionarioDTO {

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @NotBlank(message = "Telefone é obrigatório")
    private String telefone;

    @NotBlank(message = "Cargo é obrigatório")
    private String cargo;

    private boolean isAdm; // Novo campo

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public boolean isAdm() {
        return isAdm;
    }

    public void setAdm(boolean isAdm) {
        this.isAdm = isAdm;
    }
}
