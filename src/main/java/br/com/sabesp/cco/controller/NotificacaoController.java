package br.com.sabesp.cco.controller;

import br.com.sabesp.cco.dto.NotificacaoDTO;
import br.com.sabesp.cco.service.NotificacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notificacoes")
@RequiredArgsConstructor
public class NotificacaoController {

    private final NotificacaoService notificacaoService;

    @GetMapping("/listar-notificacoes")
    public ResponseEntity<?> listar(Pageable pageable) {
        return ResponseEntity.ok(notificacaoService.findAllNotificacoes(pageable));
    }

    @DeleteMapping("/excluir-notificacao/{id}")
    public ResponseEntity<?> excluir(@PathVariable Long id) {
        if (notificacaoService.excluir(id)) {
            return ResponseEntity.ok().body("Notificação excluida com sucesso!");
        }
        return ResponseEntity.internalServerError().body("Erro ao excluir notificação");
    }

    @PostMapping("/incluir-notificacao")
    public ResponseEntity<?> incluir(@RequestBody NotificacaoDTO notificacaoDTO) {
        final var notificacaoResponseDTO = notificacaoService.incluir(notificacaoDTO);
        return ResponseEntity.ok(notificacaoResponseDTO);
    }
}
