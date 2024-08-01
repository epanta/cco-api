package br.com.sabesp.cco.service;

import br.com.sabesp.cco.dto.NotificacaoDTO;
import br.com.sabesp.cco.dto.PageDTO;
import br.com.sabesp.cco.entity.Notificacao;
import br.com.sabesp.cco.mapper.NotificacaoMapper;
import br.com.sabesp.cco.repository.NotificacaoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class NotificacaoServiceTest {

    @Mock
    private NotificacaoRepository notificacaoRepository;

    @Mock
    private NotificacaoMapper notificacaoMapper;

    @InjectMocks
    private NotificacaoService notificacaoService;

    @Test
    void testFindAllNotificacoes() {
        // Arrange
        Pageable pageable = PageRequest.of(0, 10);
        List<Notificacao> notificacoes = List.of(new Notificacao(), new Notificacao());
        Page<Notificacao> notificacaoPage = new PageImpl<>(notificacoes);
        when(notificacaoRepository.findAll(pageable)).thenReturn(notificacaoPage);
        when(notificacaoMapper.toDto(notificacaoPage)).thenReturn(PageDTO.builder()
                .content(List.of(NotificacaoDTO.builder().build(),
                        NotificacaoDTO.builder().build()))
                .build());

        // Act
        PageDTO result = notificacaoService.findAllNotificacoes(pageable);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.getContent().size());
    }

    @Test
    void testExcluir() {
        // Arrange
        Long id = 1L;
        Mockito.doNothing().when(notificacaoRepository).deleteById(id);

        // Act
        boolean result = notificacaoService.excluir(id);

        // Assert
        assertTrue(result);
    }

    @Test
    void testExcluir_Exception() {
        // Arrange
        Long id = 1L;
        Mockito.doThrow(new RuntimeException()).when(notificacaoRepository).deleteById(id);

        // Act
        boolean result = notificacaoService.excluir(id);

        // Assert
        assertFalse(result);
    }

    @Test
    void testIncluir() {
        // Arrange
        NotificacaoDTO notificacaoDTO = new NotificacaoDTO();
        Notificacao notificacao = new Notificacao();
        when(notificacaoMapper.toModel(notificacaoDTO)).thenReturn(notificacao);
        when(notificacaoRepository.save(notificacao)).thenReturn(notificacao);
        when(notificacaoMapper.toDto(notificacao)).thenReturn(new NotificacaoDTO());

        // Act
        NotificacaoDTO result = notificacaoService.incluir(notificacaoDTO);

        // Assert
        assertNotNull(result);
    }
}