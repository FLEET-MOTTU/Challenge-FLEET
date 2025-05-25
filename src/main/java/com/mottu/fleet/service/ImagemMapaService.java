package com.mottu.fleet.service;

import com.mottu.fleet.dto.MapaUploadResponseDTO;
import com.mottu.fleet.model.ImagemMapa;
import com.mottu.fleet.repository.ImagemMapaRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class ImagemMapaService {

    @Value("${upload.mapa.diretorio:src/main/resources/static/images}")
    private String pastaDestino;

    private final ImagemMapaRepository imagemMapaRepository;

    public ImagemMapaService(ImagemMapaRepository imagemMapaRepository) {
        this.imagemMapaRepository = imagemMapaRepository;
    }

    public MapaUploadResponseDTO salvarImagem(MultipartFile file, String tipo) throws IOException {
        String extensao = file.getOriginalFilename()
                .substring(file.getOriginalFilename().lastIndexOf("."));
        String nomeArquivo = "mapa_" + tipo.toLowerCase() + "_" + UUID.randomUUID() + extensao;

        File pasta = new File(pastaDestino);
        if (!pasta.exists()) pasta.mkdirs();

        Path destino = new File(pasta, nomeArquivo).toPath();
        Files.copy(file.getInputStream(), destino, StandardCopyOption.REPLACE_EXISTING);

        ImagemMapa imagem = new ImagemMapa();
        imagem.setNomeArquivo(nomeArquivo);
        imagem.setTipo(tipo);
        imagem.setCaminho("/images/" + nomeArquivo);
        imagem.setDataUpload(LocalDateTime.now());
        imagemMapaRepository.save(imagem);

        return new MapaUploadResponseDTO(nomeArquivo, "/images/" + nomeArquivo);
    }

    public Optional<ImagemMapa> buscarMaisRecentePorTipo(String tipo) {
        return imagemMapaRepository.findTopByTipoOrderByDataUploadDesc(tipo);
    }
}
