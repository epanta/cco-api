/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sabesp.cco.authentication.config;

import br.com.sabesp.cco.authentication.utils.TokenUtil;
import br.com.sabesp.cco.entity.Usuario;
import br.com.sabesp.cco.service.UsuarioService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Whitlock
 */
@Component
public class JwtFilter extends OncePerRequestFilter {

    private final TokenUtil tokenUtil;

    private final UsuarioService loginService;

    public JwtFilter(TokenUtil tokenUtil, UsuarioService loginService) {
        this.tokenUtil = tokenUtil;
        this.loginService = loginService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        if (!request.getRequestURI().contains("/auth") &&
                !request.getRequestURI().contains("/visualizar-xml-medicao")) {
            String header = request.getHeader(HttpHeaders.AUTHORIZATION);

            if (ObjectUtils.isEmpty(header) || !header.startsWith("Bearer ")) {
                chain.doFilter(request, response);
                return;
            }

            String token = header.split(" ")[1].trim();
            Usuario login = tokenUtil.validateToken(token, loginService);

            if (ObjectUtils.isEmpty(login)) {
                chain.doFilter(request, response);
                return;
            }

            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(login.getPerfil().getDeclaringClass().getName()));
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(login.getMatricula(), null, authorities);

            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        chain.doFilter(request, response);
    }

}
