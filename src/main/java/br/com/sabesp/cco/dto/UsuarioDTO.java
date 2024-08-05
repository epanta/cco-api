package br.com.sabesp.cco.dto;

import jakarta.validation.constraints.Email;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class UsuarioDTO {

    private String nome;
    private String matricula;
    private String login;
    private String unidade;
    private String perfil;
    private String telefone;
    private String ramal;

    @Email
    private String email;
    private MunicipioDTO municipio;
    private String centroCusto;

    private LocalDateTime dtCriacao;

    private LocalDateTime dtAtualizacao;

    private boolean ativo;

    private boolean novoAcesso;
}
