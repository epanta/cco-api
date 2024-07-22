package br.com.sabesp.cco.controller;

import br.com.sabesp.cco.entity.Notificacao;
import br.com.sabesp.cco.service.NotificacaoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class NotificacaoController {

    private NotificacaoService notificacaoService;

    @GetMapping("/listar-notificacoes")
    public ResponseEntity<Page<Notificacao>> listar(Pageable pageable) {
        return ResponseEntity.ok(notificacaoService.findAllNotificacoes(pageable));
    }

    @DeleteMapping("/excluir-notificacao/{id}")
    public ResponseEntity<?> excluir(@PathVariable Long id) {
        if (notificacaoService.excluir(id)) {
            return ResponseEntity.ok().body("Notificação excluida com sucesso!");
        }
        return ResponseEntity.internalServerError().body("Erro ao excluir notificação");
    }
}
