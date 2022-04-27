package com.bercalini.vaga.desassembler;

import com.bercalini.vaga.dto.VagaCandidatoDTO;
import com.bercalini.vaga.model.VagaCandidato;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VagaCandidatoDesassembler {

    @Autowired
    private ModelMapper modelMapper;

    public VagaCandidatoDTO converterVagaCandidatoTOVagaCandidatoDTO(VagaCandidato vagaCandidato) {
        return modelMapper.map(vagaCandidato, VagaCandidatoDTO.class);
    }

}
