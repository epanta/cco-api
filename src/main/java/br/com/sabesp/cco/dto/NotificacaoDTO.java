package br.com.sabesp.cco.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificacaoDTO {

    private Integer id;

    private String mensagem;

    private String dataHora;

    private String funcionalidade;

    private String dataPlanilha;

    private String municipio;

    private String ultimoAjuste;

    private String operadorResponsavel;

    private Integer prazoAjuste;

    private String motivo;

    private String dataLiberacao;

    private String dataFinal;
}
