package com.bercalini.vaga.service.impl;

import com.bercalini.vaga.service.UtilService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UtilServiceImpl implements UtilService {

    @Override
    public String criarUrlCandidato(UUID vagaId, Pageable pageable) {
        return "/candidatos?vagaId=" + vagaId + "&page=" + pageable.getPageNumber() +
                "&size=" + pageable.getPageSize() + "&sort=" + pageable.getSort().toString().replaceAll(": ", ",");
    }
}
