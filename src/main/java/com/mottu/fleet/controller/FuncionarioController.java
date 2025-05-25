package com.mottu.fleet.controller;

import com.mottu.fleet.dto.CreateFuncionarioDTO;
import com.mottu.fleet.dto.FuncionarioResponseDTO;
import com.mottu.fleet.model.Funcionario;
import com.mottu.fleet.service.FuncionarioService;
import com.mottu.fleet.repository.FuncionarioRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    private final FuncionarioRepository funcionarioRepository;
    private final FuncionarioService funcionarioService;

    public FuncionarioController(FuncionarioRepository funcionarioRepository, FuncionarioService funcionarioService) {
        this.funcionarioRepository = funcionarioRepository;
        this.funcionarioService = funcionarioService;
    }

    // POST - Criar funcionário com código mockado
    @PostMapping
    public ResponseEntity<Funcionario> cadastrar(@RequestBody @Valid CreateFuncionarioDTO dto) {
        Funcionario funcionario = funcionarioService.cadastrar(dto);
        return new ResponseEntity<>(funcionario, HttpStatus.CREATED);
    }

    // GET - Listar todos os funcionários
    @GetMapping
    public ResponseEntity<List<FuncionarioResponseDTO>> listar() {
        List<FuncionarioResponseDTO> lista = funcionarioRepository.findAll().stream().map(funcionario -> {
            FuncionarioResponseDTO dto = new FuncionarioResponseDTO();
            dto.setId(funcionario.getId());
            dto.setNome(funcionario.getNome());
            dto.setTelefone(funcionario.getTelefone());
            dto.setCargo(funcionario.getCargo());
            dto.setStatus(funcionario.getStatus().toString());
            dto.setUltimoLogin(funcionario.getUltimoLogin());
            return dto;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(lista);
    }

    // GET - Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<Funcionario> buscarPorId(@PathVariable UUID id) {
        Optional<Funcionario> funcionario = funcionarioRepository.findById(id);
        return funcionario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // PUT - Atualizar funcionário
    @PutMapping("/{id}")
    public ResponseEntity<Funcionario> atualizar(@PathVariable UUID id, @RequestBody @Valid CreateFuncionarioDTO dto) {
        Optional<Funcionario> optional = funcionarioRepository.findById(id);
        if (optional.isEmpty()) return ResponseEntity.notFound().build();

        Funcionario funcionario = optional.get();
        funcionario.setNome(dto.getNome());
        funcionario.setTelefone(dto.getTelefone());
        funcionario.setCargo(dto.getCargo());
        funcionario.setIsAdm(dto.isAdm());
        funcionarioRepository.save(funcionario);

        return ResponseEntity.ok(funcionario);
    }

    // DELETE - Remover funcionário
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        if (!funcionarioRepository.existsById(id)) return ResponseEntity.notFound().build();
        funcionarioRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
