package com.bercalini.vaga.input;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VagaCandidatoInput {
    private UUID vagaId;
    @NotNull
    private UUID candidatoId;
}
