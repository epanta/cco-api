package br.com.sabesp.cco.repository;

import br.com.sabesp.cco.entity.OpcoesParametro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OpcoesParametroRepository extends JpaRepository<OpcoesParametro, Long> {

}
