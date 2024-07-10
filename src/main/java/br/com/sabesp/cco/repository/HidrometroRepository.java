package br.com.sabesp.cco.repository;

import br.com.sabesp.cco.entity.Hidrometro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HidrometroRepository extends JpaRepository<Hidrometro, Long> {

    Page<Hidrometro> findAll(Pageable pageable);
}
