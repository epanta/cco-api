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
public class Endereco {

    @Id
    @GeneratedValue(generator = "ENDERECO_SEQ", strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name="ENDERECO_SEQ", sequenceName = "ENDERECO_SEQ", allocationSize=1)
    @Column(name = "ID_ENDERECO")
    private Long id;

    @Column(name = "CEP")
    private String cep;

    @Column(name = "ESTADO")
    private String estado;

    @Column(name = "CIDADE")
    private String cidade;

    @Column(name = "BAIRRO")
    private String bairro;

    @Column(name = "LOGRADOURO")
    private String logradouro;

    @Column(name = "COMPLEMENTO")
    private String complemento;

}
