package br.com.sabesp.cco.controller;

import br.com.sabesp.cco.dto.ComunicacaoInternaDTO;
import br.com.sabesp.cco.entity.ComunicacaoInterna;
import br.com.sabesp.cco.service.ComunicacaoInternaService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class ComunicacaoInternaController {

    private final ComunicacaoInternaService comunicacaoInternaService;

    public ComunicacaoInternaController(ComunicacaoInternaService comunicacaoInternaService) {
        this.comunicacaoInternaService = comunicacaoInternaService;
    }

    @GetMapping("/listar-comunicacoes")
    public ResponseEntity<Page<ComunicacaoInterna>> listar(Pageable pageable) {
        return ResponseEntity.ok(comunicacaoInternaService.getAll(pageable));
    }

    @PostMapping("/comunicacao-interna")
    public ResponseEntity<ComunicacaoInterna> permissao(@RequestBody ComunicacaoInternaDTO comunicacaoInternaDTO) {
        return ResponseEntity.ok(comunicacaoInternaService.saveComunicacao(comunicacaoInternaDTO));
    }

}
