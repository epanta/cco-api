package br.com.sabesp.cco.service;

import br.com.sabesp.cco.dto.UsuarioDTO;
import br.com.sabesp.cco.entity.Endereco;
import br.com.sabesp.cco.entity.Usuario;
import br.com.sabesp.cco.entity.enums.PerfilEnum;
import br.com.sabesp.cco.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.Date;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

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
            Usuario usuario = toEntity(dto);
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
        copyNonNullProperties(dto, usuario);
        return usuarioRepository.save(usuario);
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

    private static Usuario toEntity(UsuarioDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setMatricula(dto.getMatricula());
        usuario.setEmail(dto.getEmail());
        usuario.setNome(dto.getNome());
        usuario.setFuncao(dto.getFuncao());
        usuario.setPerfil(PerfilEnum.from(dto.getPerfil()));
        usuario.setUnidade(dto.getUnidade());
        usuario.setTelefone(dto.getTelefone());
        usuario.setDtCriacao(new Date());
        usuario.setDtAtualizacao(new Date());
        usuario.setEndereco(Endereco.builder()
                .cidade(dto.getCidade())
                .cep(dto.getCep())
                .estado(dto.getEstado())
                .bairro(dto.getBairro())
                .complemento(dto.getComplemento())
                .logradouro(dto.getLogradouro())
                .build());
        return usuario;
    }

    private void copyNonNullProperties(Object src, Object target) {
        Field[] fields = src.getClass().getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                Object value = field.get(src);
                if (value != null) {
                    Field targetField = target.getClass().getDeclaredField(field.getName());
                    targetField.setAccessible(true);
                    targetField.set(target, value);
                }
            } catch (NoSuchFieldException | IllegalAccessException e) {
                // Log or handle the exception as needed
            }
        }
    }

}
