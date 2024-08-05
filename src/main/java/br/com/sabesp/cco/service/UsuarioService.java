package br.com.sabesp.cco.service;

import br.com.sabesp.cco.dto.UsuarioDTO;
import br.com.sabesp.cco.entity.Usuario;
import br.com.sabesp.cco.mapper.UsuarioMapper;
import br.com.sabesp.cco.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioMapper usuarioMapper;

    private final UsuarioRepository usuarioRepository;

    public Usuario findByMatriculaOrEmail(String matriculaOrEmail) {
        return usuarioRepository.findByMatriculaOrEmail(matriculaOrEmail, matriculaOrEmail);
    }

    public Page<Usuario> findAllUsuarios(Pageable pageable) {
        return usuarioRepository.findAll(pageable);
    }

    public Page<Usuario> findAllUsuariosParaAprovacao(Pageable pageable) {
        return usuarioRepository.findAllUsuariosParaAprovacao(pageable);
    }

    @Transactional
    public Usuario createUsuarioSemPermissao(UsuarioDTO dto) {
        if (dto.getMatricula() != null) {
            Usuario usuario = usuarioMapper.toEntity(dto);
            usuario.setNovoAcesso(true);
            usuario.setAtivo(false);
            return usuarioRepository.save(usuario);
        } else {
            return null;
        }
    }

    @Transactional
    public Usuario atualizarUsuario(Long id, UsuarioDTO dto) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
        final var usuarioAtualizado = usuarioMapper.updateUsuarioFromDTO(dto, usuario);
        return usuarioRepository.save(usuarioAtualizado);
    }

    @Transactional
    public Usuario alterarSituacao(Long id, boolean ativo) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
        usuario.setAtivo(ativo);
        return usuarioRepository.save(usuario);
    }

    @Transactional
    public Usuario permitirAcesso(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
        usuario.setAtivo(true);
        usuario.setNovoAcesso(false);
        return usuarioRepository.save(usuario);
    }

    public Usuario buscarUsuario(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
    }
}
