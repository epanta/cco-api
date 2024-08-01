package br.com.sabesp.cco.entity;


import br.com.sabesp.cco.entity.enums.TipoAcaoEnum;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Table(name = "TB_NOTIFICACAO")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EnableJpaRepositories(basePackages = "br.com.sabesp.cco.repository")
@Entity
@Builder
public class Notificacao {

    @Id
    @GeneratedValue(generator = "NOTIFICACAO_SEQ", strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name="NOTIFICACAO_SEQ", sequenceName = "NOTIFICACAO_SEQ", allocationSize=1)
    private Long id;

    @Column(name = "MENSAGEM")
    private String mensagem;

    @Column(name = "DATA_HORA")
    private LocalDateTime dataHora;

    @Column(name = "FUNCIONALIDADE")
    @Enumerated(EnumType.STRING)
    private TipoAcaoEnum funcionalidade;

    @Column(name = "DATA_PLANILHA")
    private LocalDate dataPlanilha;

    @Column(name = "MUNICIPIO")
    private String municipio;

    @Column(name = "ULTIMO_AJUSTE")
    private LocalDateTime ultimoAjuste;

    @Column(name = "OPERADOR_RESPONSAVEL")
    private String operadorResponsavel;

    @Column(name = "PRAZO_AJUSTE")
    private Integer prazoAjuste;

    @Column(name = "MOTIVO")
    private String motivo;

    @Column(name = "DATA_LIBERACAO")
    private LocalDate dataLiberacao;

    @Column(name = "DATA_FINAL")
    private LocalDate dataFinal;
}
