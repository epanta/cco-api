package br.com.sabesp.cco.service;

import br.com.sabesp.cco.entity.Notificacao;
import br.com.sabesp.cco.repository.NotificacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificacaoService {

    private final NotificacaoRepository notificacaoRepository;

    public Page<Notificacao> findAllNotificacoes(Pageable pageable) {
        return notificacaoRepository.findAll(pageable);
    }

    public boolean excluir(Long id) {
        try {
            notificacaoRepository.deleteById(id);
            return Boolean.TRUE;
        } catch (Exception e) {
            return Boolean.FALSE;
        }
    }
}
