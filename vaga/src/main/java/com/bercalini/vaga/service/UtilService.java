package com.bercalini.vaga.service;

import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface UtilService {
    String criarUrlCandidato(UUID vagaId, Pageable pageable);
}
