package com.mottu.fleet.config;

import com.mottu.fleet.model.Funcionario;
import com.mottu.fleet.model.Moto;
import com.mottu.fleet.model.Zona;
import com.mottu.fleet.repository.FuncionarioRepository;
import com.mottu.fleet.repository.MotoRepository;
import com.mottu.fleet.repository.ZonaRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DataSeeder {

    private final ZonaRepository zonaRepository;
    private final FuncionarioRepository funcionarioRepository;
    private final MotoRepository motoRepository;

    public DataSeeder(ZonaRepository zonaRepository, FuncionarioRepository funcionarioRepository, MotoRepository motoRepository) {
        this.zonaRepository = zonaRepository;
        this.funcionarioRepository = funcionarioRepository;
        this.motoRepository = motoRepository;
    }

    @PostConstruct
    public void seed() {
        if (zonaRepository.count() == 0) {
            Zona zona1 = new Zona(1L, "Zona Aprovadas", "APTAS");
            Zona zona2 = new Zona(2L, "Zona Manutenção", "MANUTENCAO");
            zonaRepository.save(zona1);
            zonaRepository.save(zona2);

            if (funcionarioRepository.count() == 0) {
                Funcionario adm = new Funcionario();
                adm.setNome("Administrador Padrao");
                adm.setTelefone("11999999999");
                adm.setCargo("Supervisor");
                adm.setStatus(Funcionario.Status.ATIVO);
                adm.setIsAdm(true);
                adm.setCodigo("FUNC-ADM01");
                funcionarioRepository.save(adm);

                Funcionario comum = new Funcionario();
                comum.setNome("Funcionario Padrao");
                comum.setTelefone("11988888888");
                comum.setCargo("Reboque");
                comum.setStatus(Funcionario.Status.ATIVO);
                comum.setIsAdm(false);
                comum.setCodigo("FUNC-COM01");
                funcionarioRepository.save(comum);
            }

            if (motoRepository.count() == 0) {
                Moto moto1 = new Moto();
                moto1.setPlaca("AAA1234");
                moto1.setStatus("APTAS");
                moto1.setTagBle("BLE-001");
                moto1.setZona(zona1);

                Moto moto2 = new Moto();
                moto2.setPlaca("BBB5678");
                moto2.setStatus("MANUTENCAO");
                moto2.setTagBle("BLE-002");
                moto2.setZona(zona2);

                motoRepository.save(moto1);
                motoRepository.save(moto2);
            }
        }
    }
}
