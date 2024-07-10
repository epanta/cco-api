package br.com.sabesp.cco.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class ComunicacaoInternaDTO {

    private String numeroFormulario;

    private LocalDateTime dtCriacao;

    private LocalDateTime dtAtualizacao;

    private String documentoMaterial;

    private String assunto;

    private String envioMedidoresLaudo;

    private String situacao;

    private String usuarioResponsavel;

    private String corresponsavel;

    private List<HidrometroDTO> hidrometros;
}
