package br.com.sabesp.cco.controller;

import br.com.sabesp.cco.dto.ParametroDTO;
import br.com.sabesp.cco.entity.OpcoesParametro;
import br.com.sabesp.cco.entity.Parametro;
import br.com.sabesp.cco.service.ParametroService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
public class ParametroController {

    private final ParametroService parametroService;

    public ParametroController(ParametroService parametroService) {
        this.parametroService = parametroService;
    }

    @GetMapping("/listar-parametros")
    public List<Parametro> getAllParametros() {
        return parametroService.getAllParametros();
    }

    @GetMapping("/{id}/opcoes")
    public List<OpcoesParametro> getOpcoesByParametroId(@PathVariable Long id) {
        return parametroService.getOpcoesByParametroId(id);
    }

    @PostMapping("/parametro")
    public ResponseEntity<Parametro> permissao(@RequestBody ParametroDTO parametroDTO) {
        return ResponseEntity.ok(parametroService.saveParametro(parametroDTO));
    }


}
