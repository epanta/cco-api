package br.com.sabesp.cco.controller;

import br.com.sabesp.cco.dto.PageDTO;
import br.com.sabesp.cco.dto.UsuarioDTO;
import br.com.sabesp.cco.entity.Usuario;
import br.com.sabesp.cco.mapper.UsuarioMapper;
import br.com.sabesp.cco.service.UsuarioService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UsuarioControllerTest {

    @InjectMocks
    private UsuarioController usuarioController;

    @Mock
    private UsuarioMapper usuarioMapper;

    @Mock
    private UsuarioService usuarioService;

    @Test
    public void testListarUsuarios() {
        
        Page<Usuario> usuarios = Page.empty();
        Page<UsuarioDTO> usuariosDTO = Page.empty();
        when(usuarioService.findAllUsuarios(any(Pageable.class))).thenReturn(usuarios);

        final var usuarioDTOList = Collections.singletonList((usuariosDTO));

        when(usuarioMapper.toDto(any(Page.class))).thenReturn(PageDTO.builder()
                .content(usuarioDTOList)
                .build());

        final var response = usuarioController.listar(Pageable.unpaged());

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(usuarioDTOList, response.getBody().getContent());
    }

    @Test
    public void testListarUsuariosAprovacao() {
        Page<Usuario> usuarios = Page.empty();
        Page<UsuarioDTO> usuariosDTO = Page.empty();
        when(usuarioService.findAllUsuariosParaAprovacao(any(Pageable.class))).thenReturn(usuarios);
        final var usuarioDTOList = Collections.singletonList((usuariosDTO));

        when(usuarioMapper.toDto(any(Page.class))).thenReturn(PageDTO.builder()
                .content(usuarioDTOList)
                .build());

        final var response = usuarioController.listarAprovacao(Pageable.unpaged());

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(usuarioDTOList, response.getBody().getContent());
    }

    @Test
    public void testPermissao() {
        final var usuario = Usuario.builder().build();
        final var usuarioDTO = UsuarioDTO.builder().build();
        final var usuarioDTOResponse = UsuarioDTO.builder().build();

        when(usuarioService.createUsuarioSemPermissao(any(UsuarioDTO.class))).thenReturn(usuario);
        when(usuarioMapper.toDTO(usuario)).thenReturn(usuarioDTO);

        final var response = usuarioController.permissao(usuarioDTOResponse);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(usuarioDTOResponse, response.getBody());      }

    @Test
    public void testAtualizar() {
        final var id = 1L;
        final var usuario = Usuario.builder().build();
        final var usuarioDTO = UsuarioDTO.builder().build();
        final var usuarioDTOResponse = UsuarioDTO.builder().build();

        when(usuarioService.atualizarUsuario(id, usuarioDTO)).thenReturn(usuario);
        when(usuarioMapper.toDTO(usuario)).thenReturn(usuarioDTOResponse);

        final var response = usuarioController.atualizar(id, usuarioDTO);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(usuarioDTOResponse, response.getBody());        }

    @Test
    public void testAlterarSituacao() {
        final var id = 1L;
        boolean ativo = true;
        final var usuario = Usuario.builder().build();
        final var usuarioDTO = UsuarioDTO.builder().build();
        when(usuarioService.alterarSituacao(id, ativo)).thenReturn(usuario);
        when(usuarioMapper.toDTO(usuario)).thenReturn(usuarioDTO);

        final var response = usuarioController.alterarSituacao(id, ativo);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(usuarioDTO, response.getBody());        }

    @Test
    public void testBuscarUsuario() {
        final var id = 1L;
        final var usuario = Usuario.builder().build();

        final var usuarioDTO = UsuarioDTO.builder().build();
        when(usuarioService.buscarUsuario(id)).thenReturn(usuario);
        when(usuarioMapper.toDTO(usuario)).thenReturn(usuarioDTO);

        final var response = usuarioController.buscarUsuario(id);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(usuarioDTO, response.getBody());
    }
}