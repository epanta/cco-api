package br.com.sabesp.cco.repository;

import br.com.sabesp.cco.entity.ComunicacaoInterna;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComunicacaoInternaRepository extends JpaRepository<ComunicacaoInterna, Long> {

}
