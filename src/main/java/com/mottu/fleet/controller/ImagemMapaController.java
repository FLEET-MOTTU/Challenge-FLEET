package com.mottu.fleet.controller;

import com.mottu.fleet.dto.MapaUploadResponseDTO;
import com.mottu.fleet.service.ImagemMapaService;
import com.mottu.fleet.model.ImagemMapa;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/mapa")
public class ImagemMapaController {

    private final ImagemMapaService imagemMapaService;

    public ImagemMapaController(ImagemMapaService imagemMapaService) {
        this.imagemMapaService = imagemMapaService;
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadImagem(@RequestParam("file") MultipartFile file,
                                          @RequestParam("tipo") String tipo) {
        try {
            MapaUploadResponseDTO response = imagemMapaService.salvarImagem(file, tipo);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao salvar imagem: " + e.getMessage());
        }
    }

    @GetMapping("/recente/{tipo}")
    public ResponseEntity<?> buscarMaisRecentePorTipo(@PathVariable String tipo) {
        return imagemMapaService.buscarMaisRecentePorTipo(tipo)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
