package com.bercalini.vaga.repository;

import com.bercalini.vaga.model.Vaga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface VagaRepository extends JpaRepository<Vaga, UUID>, JpaSpecificationExecutor<Vaga> {
}
