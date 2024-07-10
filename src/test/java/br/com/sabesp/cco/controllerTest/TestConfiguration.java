package br.com.sabesp.cco.controllerTest;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@EnableAutoConfiguration
@Profile("test")
public class TestConfiguration {
    // Configurações específicas de teste podem ser colocadas aqui
}

