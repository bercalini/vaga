package com.bercalini.vaga.input;

import com.bercalini.vaga.enums.Seriornidade;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class VagaInput {
    private String nome;
    private String descricao;
    private Seriornidade seriornidade;
    private BigDecimal salario;
    private UUID candidatoId;
}
