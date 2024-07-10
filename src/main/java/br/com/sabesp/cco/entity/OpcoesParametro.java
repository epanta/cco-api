package br.com.sabesp.cco.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "TB_OPCOES_PARAMETRO")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OpcoesParametro {

    @Id
    @GeneratedValue(generator = "OPCOES_PARAMETRO_SEQ", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name="OPCOES_PARAMETRO_SEQ", sequenceName = "OPCOES_PARAMETRO_SEQ", allocationSize=1)
    @Column(name = "ID_PARAMETRO")
    private Long id;

    @Column(name = "DS_DESCRITIVO", nullable = false)
    private String descritivo;

    private boolean status;
}
