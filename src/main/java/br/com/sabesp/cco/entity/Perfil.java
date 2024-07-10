package br.com.sabesp.cco.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "TB_PERFIL")
@Getter
@Setter
@Builder
@AllArgsConstructor
public class Perfil {

  @Id 
  @GeneratedValue(generator = "PERFIL_SEQ", strategy = GenerationType.IDENTITY)
  @SequenceGenerator(name="PERFIL_SEQ", sequenceName = "PERFIL_SEQ", allocationSize=1)
  @Column(name = "ID_PERFIL")
  private Long id;
  
  @Column(name = "DE_PERFIL")
  private String dePerfil;
  
  @Column(name = "ID_USUARIO_CRIACAO")
  private Integer usuarioCriacao;
  
  @Column(name = "ID_USUARIO_ATUALIZACAO")
  private Integer usuarioAtualizacao;
  
  @Column(name = "DT_CRIACAO")
  private Date dtCriacao;

  @Column(name = "DT_ATUALIZACAO")
  private Date dtAtualizacao;
  
  public Perfil() {}
 
}