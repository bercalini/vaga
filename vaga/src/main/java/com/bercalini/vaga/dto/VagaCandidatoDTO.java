package com.bercalini.vaga.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class VagaCandidatoDTO {
    private UUID vagaCandidatoId;
    private UUID candidatoId;
    private VagaDTO vaga;
}
