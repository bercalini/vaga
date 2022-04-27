package com.bercalini.vaga.service;

import com.bercalini.vaga.dto.CandidatoDTO;
import com.bercalini.vaga.dto.VagaCandidatoDTO;
import com.bercalini.vaga.input.VagaCandidatoInput;
import com.bercalini.vaga.model.VagaCandidato;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface VagaCandidatoService {
    VagaCandidato salvar(UUID vagaId, VagaCandidatoInput vagaCandidatoInput);
    Page<CandidatoDTO> listarCandidatosPorVagaId(UUID vagaId, Pageable pageable);
}
