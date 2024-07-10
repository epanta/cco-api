/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sabesp.cco.authentication.service;

import br.com.sabesp.cco.authentication.models.AuthenticationRequest;
import br.com.sabesp.cco.authentication.models.AuthenticationResponse;
import br.com.sabesp.cco.authentication.utils.TokenUtil;
import br.com.sabesp.cco.entity.Usuario;
import br.com.sabesp.cco.exceptions.NegocioException;
import br.com.sabesp.cco.service.UsuarioService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.regex.Pattern;

/**
 * @author Whitlock
 */
@Service
public class AuthenticationService {


    private final AuthenticationManager authenticationManager;

    private final UsuarioService loginService;

    private final TokenUtil tokenUtil;

    public AuthenticationService(AuthenticationManager authenticationManager, UsuarioService loginService, TokenUtil tokenUtil) {
        this.authenticationManager = authenticationManager;
        this.loginService = loginService;
        this.tokenUtil = tokenUtil;
    }

    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        try {
            if (isValidEmailOrMatricula(authenticationRequest.getUsername())) {
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getSenha()));
                Usuario login = loginService.findByMatriculaOrEmail(authenticationRequest.getUsername());
                return new AuthenticationResponse(login.getId(), login.getMatricula(), login.getNome(), login.getPerfil(), tokenUtil.generateToken(authenticationRequest.getUsername()), tokenUtil.generateRefreshToken(authenticationRequest.getUsername()));
            } else {
                throw new NegocioException("deve ser um e-mail ou uma matrícula no formato 000000-0");
            }
        } catch (AuthenticationException e) {
            if (e.getCause() instanceof NegocioException) {
                throw new NegocioException(e.getMessage());
            }
            throw new NegocioException("Login e/ou senha inválidos!");
        }

    }

    public void refresh(HttpServletRequest request, HttpServletResponse response) {
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (ObjectUtils.isEmpty(header) || !header.startsWith("Bearer ")) {
            throw new NegocioException("Refresh token não encontrado!");
        }

        String token = header.split(" ")[1].trim();
        Usuario login = tokenUtil.validateToken(token, loginService);

        if (ObjectUtils.isEmpty(login)) {
            throw new NegocioException("Usuário presente no token de renovação não encontrado!");
        }

        response.addHeader("x-access-token", tokenUtil.generateRefreshToken(login.getMatricula()));
    }

    private static boolean isValidEmailOrMatricula(String value) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        String matriculaRegex = "^\\d{6}-\\d$";

        Pattern emailPattern = Pattern.compile(emailRegex);
        Pattern matriculaPattern = Pattern.compile(matriculaRegex);

        return emailPattern.matcher(value).matches() || matriculaPattern.matcher(value).matches();
    }
}
