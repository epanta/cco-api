package br.com.sabesp.cco.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ParametroDTO {

    private String descritivo;

    private List<OpcoesParametroDTO> opcoes;
}
