package br.com.sabesp.cco.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EnderecoDTO {

    private String cep;
    private String estado;
    private String cidade;
    private String bairro;
    private String logradouro;
    private String complemento;

}
