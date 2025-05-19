package com.mottu.fleet.dto;

public class MotoDTO {
    private Long id;
    private String placa;
    private String status;
    private String tagBle;
    private String nomeZona;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTagBle() {
        return tagBle;
    }

    public void setTagBle(String tagBle) {
        this.tagBle = tagBle;
    }

    public String getNomeZona() {
        return nomeZona;
    }

    public void setNomeZona(String nomeZona) {
        this.nomeZona = nomeZona;
    }
}
