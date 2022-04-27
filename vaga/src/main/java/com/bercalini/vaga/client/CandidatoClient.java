package com.bercalini.vaga.client;

import com.bercalini.vaga.dto.CandidatoDTO;
import com.bercalini.vaga.dto.ResponsePageDTO;
import com.bercalini.vaga.input.VagaCandidatoInput;
import com.bercalini.vaga.model.Vaga;
import com.bercalini.vaga.model.VagaCandidato;
import com.bercalini.vaga.service.UtilService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Component
@Log4j2
public class CandidatoClient {

    @Value("${bercalini.api.url.candidato}")
    private String REQUEST_URL;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private UtilService utilService;

    public ResponseEntity<CandidatoDTO> buscarCandidatoPorId(UUID candidatoId) {
        String url = REQUEST_URL + "/candidatos/" + candidatoId;
        return restTemplate.exchange(url, HttpMethod.GET, null, CandidatoDTO.class);
    }

    public void salvarCandidatoNaVaga(UUID vagaId, UUID candidatoId) {
        String url = REQUEST_URL + "/candidato/" + candidatoId + "/vaga";
        VagaCandidatoInput vagaCandidatoInput = VagaCandidatoInput.builder().vagaId(vagaId).candidatoId(candidatoId).build();
        restTemplate.postForObject(url, vagaCandidatoInput, String.class);
    }

    public Page<CandidatoDTO> listarCandidatosPorVagaId(UUID vagaId, Pageable pageable) {
        String url = REQUEST_URL + utilService.criarUrlCandidato(vagaId, pageable);
        log.info("URL {}", url);
        ParameterizedTypeReference<ResponsePageDTO<CandidatoDTO>> parameterizedTypeReference = new ParameterizedTypeReference<ResponsePageDTO<CandidatoDTO>>() {};
        ResponseEntity<ResponsePageDTO<CandidatoDTO>> pageDTOResponseEntity = restTemplate.exchange(url, HttpMethod.GET, null, parameterizedTypeReference);
        return new PageImpl<>(pageDTOResponseEntity.getBody().getContent());
    }
}
