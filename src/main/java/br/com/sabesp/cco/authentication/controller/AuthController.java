/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sabesp.cco.authentication.controller;

import br.com.sabesp.cco.authentication.models.AuthenticationRequest;
import br.com.sabesp.cco.authentication.service.AuthenticationService;
import br.com.sabesp.cco.dto.AuthenticationDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 *
 * @author Whitlock
 */
@RestController
@RequestMapping(path = "/auth")
public class AuthController {


    private final AuthenticationService service;

    public AuthController(AuthenticationService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> auth(HttpServletRequest request, @RequestBody @Valid AuthenticationRequest authenticationRequest) {
        authenticationRequest.setSenha(request.getHeader("senha"));
        AuthenticationDTO dto = new AuthenticationDTO("SAUASDHASHUd", null);
        return ResponseEntity.ok(service.authenticate(authenticationRequest));
    }

    @GetMapping(path = "/refresh")
    public void refresh(HttpServletRequest request, HttpServletResponse response) {
        service.refresh(request, response);
    }
}
