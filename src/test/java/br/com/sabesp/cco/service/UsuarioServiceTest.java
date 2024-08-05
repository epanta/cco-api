package br.com.sabesp.cco.service;

import br.com.sabesp.cco.dto.UsuarioDTO;
import br.com.sabesp.cco.entity.Usuario;
import br.com.sabesp.cco.mapper.UsuarioMapper;
import br.com.sabesp.cco.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {

    @InjectMocks
    private UsuarioService usuarioService;

    @Mock
    private UsuarioMapper usuarioMapper;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Test
    public void testFindByMatriculaOrEmail() {
        String matriculaOrEmail = "teste";
        Usuario usuario = new Usuario();
        when(usuarioRepository.findByMatriculaOrEmail(matriculaOrEmail, matriculaOrEmail)).thenReturn(usuario);

        Usuario resultado = usuarioService.findByMatriculaOrEmail(matriculaOrEmail);

        assertEquals(usuario, resultado);
    }

    @Test
    public void testFindAllUsuarios() {
        Pageable pageable = Pageable.unpaged();
        Page<Usuario> usuarios = Page.empty();
        when(usuarioRepository.findAll(pageable)).thenReturn(usuarios);

        Page<Usuario> resultado = usuarioService.findAllUsuarios(pageable);

        assertEquals(usuarios, resultado);
    }

    @Test
    public void testFindAllUsuariosParaAprovacao() {
        Pageable pageable = Pageable.unpaged();
        Page<Usuario> usuarios = Page.empty();
        when(usuarioRepository.findAllUsuariosParaAprovacao(pageable)).thenReturn(usuarios);
        
        Page<Usuario> resultado = usuarioService.findAllUsuariosParaAprovacao(pageable);
        
        assertEquals(usuarios, resultado);
    }

    @Test
    public void testCreateUsuarioSemPermissao() {
        UsuarioDTO dto = UsuarioDTO.builder().build();
        dto.setMatricula("teste");
        Usuario usuario = new Usuario();
        when(usuarioMapper.toEntity(dto)).thenReturn(usuario);
        when(usuarioRepository.save(usuario)).thenReturn(usuario);
        
        Usuario resultado = usuarioService.createUsuarioSemPermissao(dto);

        assertNotNull(resultado);
        assertEquals(usuario, resultado);
    }

    @Test
    public void testAtualizarUsuario() {
        
        Long id = 1L;
        UsuarioDTO dto = UsuarioDTO.builder().build();
        Usuario usuario = new Usuario();
        when(usuarioRepository.findById(id)).thenReturn(java.util.Optional.of(usuario));
        when(usuarioMapper.updateUsuarioFromDTO(dto, usuario)).thenReturn(usuario);
        when(usuarioRepository.save(usuario)).thenReturn(usuario);

        Usuario resultado = usuarioService.atualizarUsuario(id, dto);

        assertNotNull(resultado);
        assertEquals(usuario, resultado);
    }

    @Test
    public void testAtualizarUsuarioNaoEncontrado() {
        Long id = 1L;
        UsuarioDTO dto = UsuarioDTO.builder().build();
        when(usuarioRepository.findById(id)).thenReturn(java.util.Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> usuarioService.atualizarUsuario(id, dto));
    }

    @Test
    public void testAlterarSituacao() {
        Long id = 1L;
        boolean ativo = true;
        Usuario usuario = new Usuario();
        when(usuarioRepository.findById(id)).thenReturn(java.util.Optional.of(usuario));
        when(usuarioRepository.save(usuario)).thenReturn(usuario);

        Usuario resultado = usuarioService.alterarSituacao(id, ativo);

        assertNotNull(resultado);
        assertEquals(usuario, resultado);
    }

    @Test
    public void testAlterarSituacaoNaoEncontrado() {
        Long id = 1L;
        boolean ativo = true;
        when(usuarioRepository.findById(id)).thenReturn(java.util.Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> usuarioService.alterarSituacao(id, ativo));
    }

    @Test
    public void testPermitirAcesso() {
        Long id = 1L;
        Usuario usuario = new Usuario();
        final var usuarioResponse = Usuario.builder()
                .ativo(true)
                .novoAcesso(false)
                .build();

        when(usuarioRepository.findById(id)).thenReturn(java.util.Optional.of(usuario));
        when(usuarioRepository.save(usuario)).thenReturn(usuarioResponse);

        Usuario resultado = usuarioService.permitirAcesso(id);

        assertNotNull(resultado);
        assertEquals(true, resultado.getAtivo());
        assertEquals(false, resultado.getNovoAcesso());
    }

    @Test
    public void testPermitirAcessoNaoEncontrado() {
        Long id = 1L;
        when(usuarioRepository.findById(id)).thenReturn(java.util.Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> usuarioService.permitirAcesso(id));
    }

    @Test
    public void testBuscarUsuario() {
        Long id = 1L;
        Usuario usuario = new Usuario();
        when(usuarioRepository.findById(id)).thenReturn(java.util.Optional.of(usuario));

        Usuario resultado = usuarioService.buscarUsuario(id);

        assertNotNull(resultado);
        assertEquals(usuario, resultado);
    }

    @Test
    public void testBuscarUsuarioNaoEncontrado() {
        Long id = 1L;
        when(usuarioRepository.findById(id)).thenReturn(java.util.Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> usuarioService.buscarUsuario(id));
    }

}