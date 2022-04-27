package com.bercalini.vaga.dto;

import com.bercalini.vaga.enums.Seriornidade;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class CandidatoDTO {
    private UUID candidatoId;
    private String primeiroNome;
    private String nomeCompleto;
    private String cpf;
    private Seriornidade seriornidade;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dataCadastro;

    private DadosPessoaisDTO dadosPessoais;
}
