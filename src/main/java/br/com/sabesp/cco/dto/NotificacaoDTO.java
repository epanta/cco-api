package br.com.sabesp.cco.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class NotificacaoDTO {

    private String mensagem;

    @JsonIgnore
    private LocalDateTime dataHora;

    private String funcionalidade;

}
