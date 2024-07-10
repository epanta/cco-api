/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sabesp.cco.authentication.service;


import br.com.sabesp.cco.entity.Usuario;
import br.com.sabesp.cco.exceptions.NegocioException;
import br.com.sabesp.cco.service.UsuarioService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Whitlock
 */
@Service
public class AuthenticationProviderService implements AuthenticationProvider {

    private final UsuarioService loginService;

    private final LdapService ldapService;

    @Value("${skipauth}")
    private Boolean skipauth;

    public AuthenticationProviderService(UsuarioService loginService, LdapService ldapService) {
        this.loginService = loginService;
        this.ldapService = ldapService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Usuario usuarioBD = loginService.findByMatriculaOrEmail(authentication.getPrincipal().toString());

        if (ObjectUtils.isEmpty(usuarioBD)) {
            throw new NegocioException("Usuário não encontrado!");
        }

        if (!skipauth) {
            if (ldapService.authenticateUser(authentication)) {
                List<GrantedAuthority> authorities = new ArrayList<>();
                authorities.add(new SimpleGrantedAuthority(usuarioBD.getPerfil().name()));

                return new UsernamePasswordAuthenticationToken(authentication.getName(), authentication.getCredentials().toString(), authorities);
            } else {
                throw new NegocioException("Usuário e/ou senha inválidos!");
            }
        }else{
            throw new NegocioException("Usuário e/ou senha inválidos!");
        }
    }

    @Override
    public boolean supports(Class<?> type) {
        return type.equals(UsernamePasswordAuthenticationToken.class);
    }

}
