package com.bercalini.vaga.service.impl;

import com.bercalini.vaga.assembler.VagaAssembler;
import com.bercalini.vaga.client.CandidatoClient;
import com.bercalini.vaga.desassembler.VagaDesassembler;
import com.bercalini.vaga.dto.VagaDTO;
import com.bercalini.vaga.exception.CandidatoNaoEncontradaException;
import com.bercalini.vaga.exception.VagaNaoEncontradaException;
import com.bercalini.vaga.input.VagaInput;
import com.bercalini.vaga.model.Vaga;
import com.bercalini.vaga.repository.VagaRepository;
import com.bercalini.vaga.service.VagaService;
import com.bercalini.vaga.specification.VagaSpecification;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpStatusCodeException;

import java.util.List;
import java.util.UUID;

@Service
@Log4j2
public class VagaServiceImpl implements VagaService {

    @Autowired
    private VagaRepository vagaRepository;
    @Autowired
    private VagaAssembler vagaAssembler;
    @Autowired
    private VagaDesassembler veVagaDesassembler;
    @Autowired
    private CandidatoClient candidatoClient;

    @Override
    @Transactional
    public VagaDTO salvar(VagaInput vagaInput) {
        Vaga vaga = vagaAssembler.converterVagaInputTOVaga(vagaInput);
        try {
            candidatoClient.buscarCandidatoPorId(vagaInput.getCandidatoId());
            Vaga vagaSalva = vagaRepository.save(vaga);
            log.info("POST /vagas, vaga salva com sucesso {} ", vagaSalva.getVagaId());
            return veVagaDesassembler.converterVagaTOVagaDTO(vagaSalva);
        } catch (HttpStatusCodeException ex) {
            throw new CandidatoNaoEncontradaException("Candidato com o id: " + vagaInput.getCandidatoId() + " não encontrado");
        }
    }

    @Override
    public Page<VagaDTO> listar(Specification<Vaga> vagaSpec, Pageable pageable) {
        Page<Vaga> vagaPage = vagaRepository.findAll(vagaSpec, pageable);
        List<VagaDTO> vagaDTOS = veVagaDesassembler.converterListVagaTOListVagaDTO(vagaPage.getContent());
        return new PageImpl<>(vagaDTOS, pageable, vagaPage.getTotalElements());
    }

    @Override
    public VagaDTO buscarPorId(UUID vagaId) {
        return veVagaDesassembler.converterVagaTOVagaDTO(verificarId(vagaId));
    }

    @Override
    @Transactional
    public VagaDTO atualizar(UUID vagaId, VagaInput vagaInput) {
        Vaga vaga = verificarId(vagaId);
        BeanUtils.copyProperties(vagaInput, vaga, "id");
        log.info("PUT /vagas, vaga atualizada com sucesso {} ", vaga.getVagaId());
        return veVagaDesassembler.converterVagaTOVagaDTO(vaga);
    }

    private Vaga verificarId(UUID vagaId) {
        return vagaRepository.findById(vagaId).orElseThrow(() -> new VagaNaoEncontradaException("Vaga com o id: " + vagaId + " não encontrada"));
    }
}
