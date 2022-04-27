package com.bercalini.vaga.service.impl;

import com.bercalini.vaga.assembler.VagaAssembler;
import com.bercalini.vaga.client.CandidatoClient;
import com.bercalini.vaga.desassembler.VagaCandidatoDesassembler;
import com.bercalini.vaga.dto.CandidatoDTO;
import com.bercalini.vaga.dto.VagaCandidatoDTO;
import com.bercalini.vaga.dto.VagaDTO;
import com.bercalini.vaga.exception.CandidatoJaInscritoNaVagaException;
import com.bercalini.vaga.input.VagaCandidatoInput;
import com.bercalini.vaga.model.Vaga;
import com.bercalini.vaga.model.VagaCandidato;
import com.bercalini.vaga.repository.VagaCandidatoRepository;
import com.bercalini.vaga.service.VagaCandidatoService;
import com.bercalini.vaga.service.VagaService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Log4j2
public class VagaCandidatoServiceImpl implements VagaCandidatoService {

    @Autowired
    private VagaCandidatoRepository vagaCandidatoRepository;
    @Autowired
    private VagaService vagaService;
    @Autowired
    private VagaAssembler vagaAssembler;
    @Autowired
    private CandidatoClient candidatoClient;
    @Autowired
    private VagaCandidatoDesassembler vagaCandidatoDesassembler;

    @Override
    @Transactional
    public VagaCandidato salvar(UUID vagaId, VagaCandidatoInput vagaCandidatoInput) {
        VagaDTO vagaDTO = vagaService.buscarPorId(vagaId);
        Vaga vaga = vagaAssembler.converterVagaDTOTOVaga(vagaDTO);
        ResponseEntity<CandidatoDTO> candidatoDTO = candidatoClient.buscarCandidatoPorId(vagaCandidatoInput.getCandidatoId());
        log.info("Candidato retornado: " + candidatoDTO);
        if (vagaCandidatoRepository.existsByVagaAndCandidatoId(vaga, vagaCandidatoInput.getCandidatoId())) {
            throw new CandidatoJaInscritoNaVagaException("Candidato com o id: " + candidatoDTO.getBody().getCandidatoId() + " já cadastrado na vaga: " + vagaId);
        }
        VagaCandidato vagaCandidato = VagaCandidato.builder().vaga(vaga).candidatoId(candidatoDTO.getBody().getCandidatoId()).build();
        vagaCandidato = vagaCandidatoRepository.save(vagaCandidato);
        candidatoClient.salvarCandidatoNaVaga(vaga.getVagaId(), candidatoDTO.getBody().getCandidatoId());
        log.info("Vaga candidato salvo som sucesso {}", vagaCandidato.getCandidatoId());
        return vagaCandidato;
        //return vagaCandidatoDesassembler.converterVagaCandidatoTOVagaCandidatoDTO(vagaCandidato);
    }

    @Override
    public Page<CandidatoDTO> listarCandidatosPorVagaId(UUID vagaId, Pageable pageable) {
        return candidatoClient.listarCandidatosPorVagaId(vagaId, pageable);
    }
}
