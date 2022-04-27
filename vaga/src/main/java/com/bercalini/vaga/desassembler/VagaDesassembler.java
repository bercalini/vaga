package com.bercalini.vaga.desassembler;

import com.bercalini.vaga.dto.VagaDTO;
import com.bercalini.vaga.input.VagaInput;
import com.bercalini.vaga.model.Vaga;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class VagaDesassembler {

    @Autowired
    private ModelMapper modelMapper;

    public VagaDTO converterVagaTOVagaDTO(Vaga vaga) {
        return modelMapper.map(vaga, VagaDTO.class);
    }

    public List<VagaDTO> converterListVagaTOListVagaDTO(List<Vaga> vagas) {
        return vagas.stream().map(v -> converterVagaTOVagaDTO(v)).collect(Collectors.toList());
    }
}
