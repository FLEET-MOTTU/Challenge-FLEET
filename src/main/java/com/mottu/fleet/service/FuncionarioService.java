package com.mottu.fleet.service;

import com.mottu.fleet.dto.CreateFuncionarioDTO;
import com.mottu.fleet.model.Funcionario;
import com.mottu.fleet.repository.FuncionarioRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;

    public FuncionarioService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public Funcionario cadastrar(CreateFuncionarioDTO dto) {
        Funcionario funcionario = new Funcionario();
        funcionario.setNome(dto.getNome());
        funcionario.setTelefone(dto.getTelefone());
        funcionario.setCargo(dto.getCargo());
        funcionario.setStatus(Funcionario.Status.ATIVO);
        funcionario.setIsAdm(dto.isAdm());

        // Geração de Código Mockado: FUNC-XXXXX
        String codigo = "FUNC-" + UUID.randomUUID().toString().substring(0, 5).toUpperCase();
        funcionario.setCodigo(codigo);

        return funcionarioRepository.save(funcionario);
    }
}
