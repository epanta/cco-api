package br.com.sabesp.cco.controller;

import br.com.sabesp.cco.dto.PageDTO;
import br.com.sabesp.cco.dto.UsuarioDTO;
import br.com.sabesp.cco.mapper.UsuarioMapper;
import br.com.sabesp.cco.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioMapper usuarioMapper;
    private final UsuarioService usuarioService;

    @GetMapping("/listar-usuarios")
    public ResponseEntity<PageDTO<UsuarioDTO>> listar(Pageable pageable) {
        final var usuarioPage = usuarioService.findAllUsuarios(pageable);
        final var usuarioPageDto = usuarioMapper.toDto(usuarioPage);
        return ResponseEntity.ok(usuarioPageDto);
    }

    @GetMapping("/listar-usuarios-aprovacao")
    public ResponseEntity<PageDTO<UsuarioDTO>> listarAprovacao(Pageable pageable) {
        final var usuarioPage = usuarioService.findAllUsuariosParaAprovacao(pageable);
        final var usuarioPageDto = usuarioMapper.toDto(usuarioPage);
        return ResponseEntity.ok(usuarioPageDto);
    }

    @PostMapping("/sem-permissao")
    public ResponseEntity<UsuarioDTO> permissao(@RequestBody UsuarioDTO usuario) {
        final var usuarioResponse = usuarioService.createUsuarioSemPermissao(usuario);
        final var usuarioDtoResponse = usuarioMapper.toDTO(usuarioResponse);
        return ResponseEntity.ok(usuarioDtoResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> atualizar(@PathVariable Long id,
                                             @RequestBody UsuarioDTO usuarioDTO) {
        final var usuarioResponse = usuarioService.atualizarUsuario(id, usuarioDTO);
        final var usuarioDtoResponse = usuarioMapper.toDTO(usuarioResponse);
        return ResponseEntity.ok(usuarioDtoResponse);
    }

    @PutMapping("/situacao/{id}/{ativo}")
    public ResponseEntity<UsuarioDTO> alterarSituacao(@PathVariable Long id,
                                             @PathVariable boolean ativo) {
        final var usuarioResponse = usuarioService.alterarSituacao(id, ativo);
        final var usuarioDtoResponse = usuarioMapper.toDTO(usuarioResponse);
        return ResponseEntity.ok(usuarioDtoResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> buscarUsuario(@PathVariable Long id) {
        final var usuarioResponse = usuarioService.buscarUsuario(id);
        final var usuarioDtoResponse = usuarioMapper.toDTO(usuarioResponse);
        return ResponseEntity.ok(usuarioDtoResponse);
    }

}
