package br.com.sabesp.cco.entity;

import br.com.sabesp.cco.entity.enums.SituacaoEnum;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "TB_COMUNICACAO_INT")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ComunicacaoInterna {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_COMUNICACAO_INT")
    private Long id;

    @Column(name = "NU_FORMULARIO", nullable = false, unique = true)
    private String numeroFormulario;

    @Column(name="DT_CRIACAO")
    private LocalDateTime dtCriacao;

    @Column(name="DT_ATUALIZACAO")
    private LocalDateTime dtAtualizacao;

    @Column(name = "DS_DOCUMENTO_MATERIAL", nullable = false)
    private String documentoMaterial;

    @Column(name = "DS_ASSUNTO", nullable = false)
    private String assunto;

    @Column(name = "ENVIO_MEDIDORES_LAUDO", nullable = false)
    private String envioMedidoresLaudo;

    @Enumerated(EnumType.STRING)
    @Column(name = "SITUACAO", nullable = false)
    private SituacaoEnum situacao;

    @ManyToOne
    @JoinColumn(name = "ID_USUARIO_CRIACAO", foreignKey = @ForeignKey(name = "FK_TB_COMUNICACAO_INT_USUARIO_CR"))
    private Usuario usuarioResponsavel;

    @ManyToOne
    @JoinColumn(name = "ID_USUARIO_CORRESPONSAVEL", foreignKey = @ForeignKey(name = "FK_TB_COMUNICACAO_INT_USUARIO_CORRESP"))
    private Usuario corresponsavel;

    @ManyToMany
    @JoinTable(
            name = "COMUNICACAO_HIDROMETRO",
            joinColumns = @JoinColumn(name = "COMUNICACAO_INTERNA_ID"),
            inverseJoinColumns = @JoinColumn(name = "HIDROMETRO_ID")
    )
    private List<Hidrometro> hidrometros;
}
