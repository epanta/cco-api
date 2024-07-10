package br.com.sabesp.cco.service;

import br.com.sabesp.cco.dto.ParametroDTO;
import br.com.sabesp.cco.entity.OpcoesParametro;
import br.com.sabesp.cco.entity.Parametro;
import br.com.sabesp.cco.repository.OpcoesParametroRepository;
import br.com.sabesp.cco.repository.ParametroRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ParametroService {

    private final ParametroRepository parametroRepository;
    private final OpcoesParametroRepository opcoesParametroRepository;

    public ParametroService(ParametroRepository parametroRepository,
                            OpcoesParametroRepository opcoesParametroRepository) {
        this.parametroRepository = parametroRepository;
        this.opcoesParametroRepository = opcoesParametroRepository;
    }

    public List<Parametro> getAllParametros() {
        return parametroRepository.findAll();
    }

    public List<OpcoesParametro> getOpcoesByParametroId(Long id) {
        return parametroRepository.findById(id)
                .map(Parametro::getOpcoesParametroList)
                .orElseThrow(() -> new RuntimeException("Parametro não encontrado"));
    }

    public Parametro saveParametro(ParametroDTO parametro) {
        Parametro parametroEntity = parametroMapper(parametro);
        opcoesParametroRepository.saveAll(parametroEntity.getOpcoesParametroList());
        return parametroRepository.save(parametroEntity);
    }

    private Parametro parametroMapper(ParametroDTO parametro) {
        List<OpcoesParametro> opcoes = new ArrayList<>();
        parametro.getOpcoes().forEach(opcoesParametroDTO -> {
            OpcoesParametro entity = new OpcoesParametro();
            entity.setStatus(opcoesParametroDTO.isStatus());
            entity.setDescritivo(opcoesParametroDTO.getDescritivo());
            opcoes.add(entity);
        });
        return Parametro.builder().descritivo(parametro.getDescritivo()).opcoesParametroList(opcoes).build();
    }
}
