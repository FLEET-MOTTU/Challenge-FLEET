package com.mottu.fleet.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateMotoDTO {

    @NotBlank(message = "A placa é obrigatória.")
    private String placa;

    @NotBlank(message = "O status é obrigatório.")
    private String status;

    private String tagBle;

    @NotNull(message = "O ID da zona é obrigatório.")
    private Long zonaId;

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

    public Long getZonaId() {
        return zonaId;
    }

    public void setZonaId(Long zonaId) {
        this.zonaId = zonaId;
    }
}
