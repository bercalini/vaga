package com.bercalini.vaga.controller;

import com.bercalini.vaga.dto.VagaDTO;
import com.bercalini.vaga.input.VagaInput;
import com.bercalini.vaga.service.VagaService;
import com.bercalini.vaga.specification.VagaSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/vagas")
public class VagaController {

    @Autowired
    private VagaService vagaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VagaDTO salvar(@RequestBody @Valid VagaInput vagaInput) {
        return vagaService.salvar(vagaInput);
    }

    @GetMapping
    public Page<VagaDTO> listar(VagaSpecification.VagaSpec vagaSpec, @PageableDefault(page = 0, size = 10, sort = "vagaId", direction = Sort.Direction.ASC)Pageable pageable,
                                @RequestParam(required = false)UUID candidatoId) {
        if (candidatoId == null) {
            return vagaService.listar(vagaSpec, pageable);
        }
        return vagaService.listar(VagaSpecification.verificarCandidatoId(candidatoId).and(vagaSpec), pageable);
    }

    @GetMapping("/{vagaId}")
    public VagaDTO buscarPorId(@PathVariable UUID vagaId) {
        return vagaService.buscarPorId(vagaId);
    }

    @PutMapping("/{vagaId}")
    public VagaDTO atualizar(@PathVariable UUID vagaId, @RequestBody @Valid VagaInput vagaInput) {
        return vagaService.atualizar(vagaId, vagaInput);
    }
}
