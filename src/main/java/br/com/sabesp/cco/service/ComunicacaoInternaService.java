package br.com.sabesp.cco.service;

import br.com.sabesp.cco.dto.ComunicacaoInternaDTO;
import br.com.sabesp.cco.entity.ComunicacaoInterna;
import br.com.sabesp.cco.entity.Hidrometro;
import br.com.sabesp.cco.entity.Usuario;
import br.com.sabesp.cco.entity.enums.SituacaoEnum;
import br.com.sabesp.cco.entity.enums.SolicitanteEnum;
import br.com.sabesp.cco.repository.ComunicacaoInternaRepository;
import br.com.sabesp.cco.repository.HidrometroRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ComunicacaoInternaService {

    private final ComunicacaoInternaRepository comunicacaoInternaRepository;
    private final HidrometroRepository hidrometroRepository;
    private final UsuarioService usuarioService;

    public ComunicacaoInternaService(ComunicacaoInternaRepository comunicacaoInternaRepository,
                                     HidrometroRepository hidrometroRepository, UsuarioService usuarioService) {
        this.comunicacaoInternaRepository = comunicacaoInternaRepository;
        this.hidrometroRepository = hidrometroRepository;
        this.usuarioService = usuarioService;
    }

    public Page<ComunicacaoInterna> getAll(Pageable pageable) {
        return comunicacaoInternaRepository.findAll(pageable);
    }

    public ComunicacaoInterna getComunicacaoById(Long id) {
        return comunicacaoInternaRepository.findById(id).orElse(null);
    }

    public ComunicacaoInterna saveComunicacao(ComunicacaoInternaDTO comunicacaoInternaDTO) {
        ComunicacaoInterna comunicacaoInterna = mapComunicacaoInterna(comunicacaoInternaDTO);
        return comunicacaoInternaRepository.save(comunicacaoInterna);
    }

    public void deleteComunicacao(Long id) {
        comunicacaoInternaRepository.deleteById(id);
    }

    private ComunicacaoInterna mapComunicacaoInterna(ComunicacaoInternaDTO comunicacaoInternaDTO) {
        List<Hidrometro> hidrometros = new ArrayList<>();
        if (comunicacaoInternaDTO.getHidrometros() != null) {
            comunicacaoInternaDTO.getHidrometros().forEach(hidrometroDTO -> {
                Hidrometro hidrometro = new Hidrometro();
                hidrometro.setNuSerie(hidrometroDTO.getNuSerie());
                hidrometro.setTotalM3(hidrometroDTO.getTotalM3());
                hidrometro.setPde(hidrometroDTO.getPde());
                hidrometro.setOsConecta(hidrometroDTO.getOsConecta());
                hidrometro.setClasse(hidrometroDTO.getClasse());
                hidrometro.setSolicitante(SolicitanteEnum.valueOf(hidrometroDTO.getSolicitante()));
                hidrometros.add(hidrometro);
            });
        }
        Usuario responsavel = usuarioService.findByMatriculaOrEmail(comunicacaoInternaDTO.getUsuarioResponsavel());
        Usuario corresponsavel = usuarioService.findByMatriculaOrEmail(comunicacaoInternaDTO.getCorresponsavel());
        return ComunicacaoInterna.builder()
                .situacao(SituacaoEnum.valueOf(comunicacaoInternaDTO.getSituacao()))
                .assunto(comunicacaoInternaDTO.getAssunto())
                .usuarioResponsavel(responsavel)
                .corresponsavel(corresponsavel)
                .documentoMaterial(comunicacaoInternaDTO.getDocumentoMaterial())
                .envioMedidoresLaudo(comunicacaoInternaDTO.getEnvioMedidoresLaudo())
                .numeroFormulario(comunicacaoInternaDTO.getNumeroFormulario())
                .hidrometros(hidrometros).build();
    }

}
