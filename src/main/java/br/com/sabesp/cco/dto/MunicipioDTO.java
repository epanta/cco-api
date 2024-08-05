package br.com.sabesp.cco.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MunicipioDTO {

    private Long id;

    private String codigo;

    private String sigla;

    private String nome;

    private String descricao;

}
