package com.bercalini.vaga.repository;

import com.bercalini.vaga.model.Vaga;
import com.bercalini.vaga.model.VagaCandidato;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface VagaCandidatoRepository extends JpaRepository<VagaCandidato, UUID> {
    boolean existsByVagaAndCandidatoId(Vaga vaga, UUID candidatoId);
}
