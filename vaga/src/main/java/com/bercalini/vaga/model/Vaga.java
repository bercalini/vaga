package com.bercalini.vaga.model;

import com.bercalini.vaga.enums.Seriornidade;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "tbl_vaga")
public class Vaga implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID vagaId;

    private String nome;
    private String descricao;
    private Seriornidade seriornidade;
    private BigDecimal salario;

    private UUID candidatoId;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "vaga")
    public List<VagaCandidato> vagaCandidatos;
}
