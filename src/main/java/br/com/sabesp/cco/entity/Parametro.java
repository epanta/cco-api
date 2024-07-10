package br.com.sabesp.cco.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "TB_PARAMETRO")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Parametro {

    @Id
    @GeneratedValue(generator = "PARAMETRO_SEQ", strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name="PARAMETRO_SEQ", sequenceName = "PARAMETRO_SEQ", allocationSize=1)
    @Column(name = "ID_PARAMETRO")
    private Long id;

    @Column(name = "DS_PARAMETRO")
    private String descritivo;

    @OneToMany
    private List<OpcoesParametro> opcoesParametroList;
}
