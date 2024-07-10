package br.com.sabesp.cco.dto;

public record AuthenticationDTO(String jwtToken, String error) {
}
