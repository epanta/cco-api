package br.com.sabesp.cco.controller;

import br.com.sabesp.cco.dto.UsuarioDTO;
import br.com.sabesp.cco.entity.Usuario;
import br.com.sabesp.cco.service.UsuarioService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/listar-usuarios")
    public ResponseEntity<Page<Usuario>> listar(Pageable pageable) {
        return ResponseEntity.ok(usuarioService.findAllUsuarios(pageable));
    }

    @GetMapping("/listar-usuarios-aprovacao")
    public ResponseEntity<Page<Usuario>> listarAprovacao(Pageable pageable) {
        return ResponseEntity.ok(usuarioService.findAllUsuariosParaAprovacao(pageable));
    }

    @PostMapping("/sem-permissao")
    public ResponseEntity<Usuario> permissao(@RequestBody UsuarioDTO usuario) {
        return ResponseEntity.ok(usuarioService.createUsuarioSemPermissao(usuario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizar(@PathVariable Long id,
                                             @RequestBody UsuarioDTO usuario) {
        return ResponseEntity.ok(usuarioService.atualizarUsuario(id, usuario));
    }

    @PutMapping("/situacao/{id}/{ativo}")
    public ResponseEntity<Usuario> alterarSituacao(@PathVariable Long id,
                                             @PathVariable boolean ativo) {
        return ResponseEntity.ok(usuarioService.alterarSituacao(id, ativo));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarUsuario(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.buscarUsuario(id));
    }

}
