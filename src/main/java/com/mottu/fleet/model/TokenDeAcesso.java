package com.mottu.fleet.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class TokenDeAcesso {

    @Id
    private String token;

    @ManyToOne
    private Funcionario funcionario;

    private LocalDateTime criadoEm;

    private LocalDateTime expiraEm;

    private boolean usado;

    private String dispositivoRegistrado;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }

    public void setCriadoEm(LocalDateTime criadoEm) {
        this.criadoEm = criadoEm;
    }

    public LocalDateTime getExpiraEm() {
        return expiraEm;
    }

    public void setExpiraEm(LocalDateTime expiraEm) {
        this.expiraEm = expiraEm;
    }

    public boolean isUsado() {
        return usado;
    }

    public void setUsado(boolean usado) {
        this.usado = usado;
    }

    public String getDispositivoRegistrado() {
        return dispositivoRegistrado;
    }

    public void setDispositivoRegistrado(String dispositivoRegistrado) {
        this.dispositivoRegistrado = dispositivoRegistrado;
    }
}
