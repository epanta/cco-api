package br.com.sabesp.cco.service;

import br.com.sabesp.cco.dto.NotificacaoDTO;
import br.com.sabesp.cco.dto.PageDTO;
import br.com.sabesp.cco.mapper.NotificacaoMapper;
import br.com.sabesp.cco.repository.NotificacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificacaoService {
    private final NotificacaoRepository notificacaoRepository;

    private final NotificacaoMapper notificacaoMapper;

    public PageDTO findAllNotificacoes(Pageable pageable) {
        final var notificacaoPage = notificacaoRepository.findAll(pageable);

        return notificacaoMapper.toDto(notificacaoPage);
    }

    public boolean excluir(Long id) {
        try {
            notificacaoRepository.deleteById(id);
            return Boolean.TRUE;
        } catch (Exception e) {
            return Boolean.FALSE;
        }
    }

    public NotificacaoDTO incluir(NotificacaoDTO notificacaoDTO) {
        final var notificacao = notificacaoMapper.toModel(notificacaoDTO);
        final var notificacaoResponse = notificacaoRepository.save(notificacao);

        return notificacaoMapper.toDto(notificacaoResponse);
    }
}
