package com.bercalini.vaga.service;

import com.bercalini.vaga.dto.VagaDTO;
import com.bercalini.vaga.input.VagaInput;
import com.bercalini.vaga.model.Vaga;
import com.bercalini.vaga.specification.VagaSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.UUID;

public interface VagaService {
    VagaDTO salvar(VagaInput vagaInput);
    Page<VagaDTO> listar(Specification<Vaga> vagaSpec, Pageable pageable);
    VagaDTO buscarPorId(UUID vagaId);
    VagaDTO atualizar(UUID vagaId, VagaInput vagaInput);
}
