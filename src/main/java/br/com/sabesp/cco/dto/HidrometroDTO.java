package br.com.sabesp.cco.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HidrometroDTO {

    private String nuSerie;

    private double totalM3;

    private String pde;

    private String osConecta;

    private String solicitante;

    private String classe;
}
