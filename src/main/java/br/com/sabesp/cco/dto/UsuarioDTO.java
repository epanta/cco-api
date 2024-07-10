package br.com.sabesp.cco.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class UsuarioDTO {

    private String nome;
    private String matricula;
    private String funcao;
    private String unidade;
    private String perfil;
    private String telefone;
    @Email
    private String email;
    private String estado;
    private String bairro;
    private String cidade;
    private String cep;
    private String logradouro;
    private String complemento;
    @JsonIgnore
    private LocalDateTime dtCriacao;
    @JsonIgnore
    private LocalDateTime dtAtualizacao;
    @JsonIgnore
    private boolean ativo;
}
