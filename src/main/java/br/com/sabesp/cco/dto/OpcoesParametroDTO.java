package br.com.sabesp.cco.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OpcoesParametroDTO {

    private String descritivo;
    private boolean status;
}
