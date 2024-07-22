package br.com.sabesp.cco.entity;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

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
    @Column(name = "ID_NOTIFICACAO")
    private Long id;

    @Column(name = "MENSAGEM")
    private String mensagem;

    @Column(name = "DATA_HORA")
    private LocalDateTime dataHora;

    @Column(name = "FUNCIONALIDADE")
    private String funcionalidade;
}
