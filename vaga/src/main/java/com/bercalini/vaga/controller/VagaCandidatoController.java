package com.bercalini.vaga.controller;

import com.bercalini.vaga.dto.CandidatoDTO;
import com.bercalini.vaga.dto.VagaCandidatoDTO;
import com.bercalini.vaga.input.VagaCandidatoInput;
import com.bercalini.vaga.model.VagaCandidato;
import com.bercalini.vaga.service.VagaCandidatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class VagaCandidatoController {

    @Autowired
    private VagaCandidatoService vagaCandidatoService;

    @PostMapping("/vaga/{vagaId}/candidato")
    @ResponseStatus(HttpStatus.CREATED)
    public VagaCandidato salvar(@PathVariable UUID vagaId, @RequestBody VagaCandidatoInput vagaCandidatoInput) {
        return vagaCandidatoService.salvar(vagaId, vagaCandidatoInput);
    }

    @GetMapping("/vaga/{vagaId}/candidatos")
    public Page<CandidatoDTO> listarCandidatosPorVagaId(@PathVariable UUID vagaId, @PageableDefault(page = 0, size = 10, sort = "candidatoId", direction = Sort.Direction.ASC)Pageable pageable) {
        return  vagaCandidatoService.listarCandidatosPorVagaId(vagaId, pageable);
    }

}
