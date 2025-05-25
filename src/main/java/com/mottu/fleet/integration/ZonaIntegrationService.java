package com.mottu.fleet.integration;

import com.mottu.fleet.dto.ZonaDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ZonaIntegrationService {

    private static final Logger logger = LoggerFactory.getLogger(ZonaIntegrationService.class);

    private final RestTemplate restTemplate;

    @Value("${integracao.zona.url}")
    private String destinoApiUrl;

    public ZonaIntegrationService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public boolean enviarZonaParaOutraApi(ZonaDTO zonaDTO) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<ZonaDTO> request = new HttpEntity<>(zonaDTO, headers);

        try {
            restTemplate.postForEntity(destinoApiUrl, request, Void.class);
            logger.info("✔ Zona enviada com sucesso para API externa.");
            return true;
        } catch (Exception e) {
            logger.error("❌ Erro ao enviar zona para outra API: {}", e.getMessage());
            return false;
        }
    }
}
