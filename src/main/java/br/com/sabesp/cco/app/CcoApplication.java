package br.com.sabesp.cco.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"br.com.sabesp.cco.repository"})
@Configuration
@ComponentScan(basePackages = {"br.com.sabesp.cco.*"})
@EntityScan("br.com.sabesp.cco.entity")
public class CcoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CcoApplication.class, args);
	}

}
