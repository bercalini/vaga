package com.bercalini.vaga.specification;

import com.bercalini.vaga.model.Vaga;
import com.bercalini.vaga.model.VagaCandidato;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Join;
import java.util.UUID;

public class VagaSpecification {
    @And({
            @Spec(path = "nome", spec = Like.class),
            @Spec(path = "descricao", spec = Like.class),
            @Spec(path = "seriornidade", spec = Equal.class),
            @Spec(path = "salario", spec = Equal.class)
    })
    public interface VagaSpec extends Specification<Vaga> {};

    public static Specification<Vaga> verificarCandidatoId(UUID candidatoId) {
        return (root, query, criteriaBuilder) -> {
            query.distinct(true);
            Join<Vaga, VagaCandidato> vagaCandidatos = root.join("vagaCandidatos");
            return criteriaBuilder.equal(vagaCandidatos.get("candidatoId"), candidatoId);
        };
    }
}
