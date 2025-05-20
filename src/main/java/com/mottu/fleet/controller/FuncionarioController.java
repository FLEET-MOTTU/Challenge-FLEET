package com.mottu.fleet.controller;

import com.mottu.fleet.dto.CreateFuncionarioDTO;
import com.mottu.fleet.model.Funcionario;
import com.mottu.fleet.repository.FuncionarioRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    private final FuncionarioRepository funcionarioRepository;

    public FuncionarioController(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    @PostMapping
    public ResponseEntity<Funcionario> cadastrar(@RequestBody @Valid CreateFuncionarioDTO dto) {
        Funcionario funcionario = new Funcionario();
        funcionario.setNome(dto.getNome());
        funcionario.setTelefone(dto.getTelefone());
        funcionario.setCargo(dto.getCargo());
        funcionario.setStatus(Funcionario.Status.ATIVO);
        funcionarioRepository.save(funcionario);
        return new ResponseEntity<>(funcionario, HttpStatus.CREATED);
    }
}
