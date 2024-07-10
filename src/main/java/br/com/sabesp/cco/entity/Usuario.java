package br.com.sabesp.cco.entity;

import br.com.sabesp.cco.entity.enums.PerfilEnum;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.type.NumericBooleanConverter;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Date;

@Table(name = "TB_USUARIO")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EnableJpaRepositories(basePackages = "br.com.sabesp.cco.repository")
@Entity
@Builder
public class Usuario {

    @Id
    @GeneratedValue(generator = "USUARIO_SEQ", strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name="USUARIO_SEQ", sequenceName = "USUARIO_SEQ", allocationSize=1)
    @Column(name = "ID_USUSARIO")
    private Long id;

    @Column(name = "NOME_USUARIO")
    private String nome;

    @Column(name = "MATRICULA_USUARIO")
    private String matricula;

    @Column(name = "FUNCAO_USUARIO")
    private String funcao;

    @Column(name = "UNI_USUARIO")
    private String unidade;

    @Column(name = "PERFIL_USUARIO")
    private PerfilEnum perfil;

    @Column(name = "TEL_USUARIO")
    private String telefone;

    @Column(name = "EMAIL_USUARIO")
    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_ENDERECO_USUARIO")
    private Endereco endereco;

    @Column(name = "DT_CRIACAO")
    private Date dtCriacao;

    @Column(name = "DT_ATUALIZACAO")
    private Date dtAtualizacao;

    @Column(name = "ATIVO")
    @Convert(converter = NumericBooleanConverter.class)
    private boolean ativo;

    @Column(name = "NOVO_ACESSO")
    @Convert(converter = NumericBooleanConverter.class)
    private boolean novoAcesso;
}
