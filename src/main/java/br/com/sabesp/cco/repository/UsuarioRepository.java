package br.com.sabesp.cco.repository;

import br.com.sabesp.cco.entity.Usuario;
import br.com.sabesp.cco.entity.enums.PerfilEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByMatriculaOrEmail(String matricula, String email);

    Page<Usuario> findByPerfil(PerfilEnum perfil, Pageable pageable);

    Page<Usuario> findAll(Pageable pageable);

    @Query(
            value = "SELECT * FROM Usuario u WHERE u.ativo = false AND u.novo_acesso = true",
            countQuery = "SELECT count(*) FROM Usuario u WHERE u.ativo = false AND u.novo_acesso = true",
            nativeQuery = true
    )
    Page<Usuario> findAllUsuariosParaAprovacao(Pageable pageable);

    Optional<Usuario> findUsuarioByMatricula(String matricula);
}
