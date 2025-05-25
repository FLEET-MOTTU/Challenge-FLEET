package com.mottu.fleet.dto;

public class MapaUploadResponseDTO {

    private String nomeArquivo;
    private String url;

    public MapaUploadResponseDTO(String nomeArquivo, String url) {
        this.nomeArquivo = nomeArquivo;
        this.url = url;
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public void setNomeArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
