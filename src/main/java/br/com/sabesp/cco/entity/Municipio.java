package br.com.sabesp.cco.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "TB_ENDERECO")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Municipio {

    @Id
    @GeneratedValue(generator = "MUNICIPIO_SEQ", strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name="MUNICIPIO_SEQ", sequenceName = "MUNICIPIO_SEQ", allocationSize=1)
    private Long id;

    @Column(name = "codigo")
    private String codigo;

    @Column(name = "sigla")
    private String sigla;

    @Column(name = "nome")
    private String nome;

    @Column(name = "descricao")
    private String descricao;

}
