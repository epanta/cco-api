package br.com.sabesp.cco.repository;

import br.com.sabesp.cco.entity.Parametro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParametroRepository extends JpaRepository<Parametro, Long> {

    Page<Parametro> findAll(Pageable pageable);
}
