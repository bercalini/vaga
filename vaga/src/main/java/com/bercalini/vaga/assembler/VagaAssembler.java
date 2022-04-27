package com.bercalini.vaga.assembler;

import com.bercalini.vaga.dto.VagaDTO;
import com.bercalini.vaga.input.VagaInput;
import com.bercalini.vaga.model.Vaga;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VagaAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public Vaga converterVagaInputTOVaga(VagaInput vagaInput) {
        return modelMapper.map(vagaInput, Vaga.class);
    }
    public Vaga converterVagaDTOTOVaga(VagaDTO vagaDTO) {
        return modelMapper.map(vagaDTO, Vaga.class);
    }

}
