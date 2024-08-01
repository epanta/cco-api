package br.com.sabesp.cco.controller;

import br.com.sabesp.cco.dto.NotificacaoDTO;
import br.com.sabesp.cco.dto.PageDTO;
import br.com.sabesp.cco.service.NotificacaoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class NotificacaoControllerTest {

    @Mock
    private NotificacaoService notificacaoService;

    @InjectMocks
    private NotificacaoController notificacaoController;

    @Test
    void testListarNotificacoes() {
        // Arrange
        Pageable pageable = PageRequest.of(0, 10);
        PageDTO notificacaoPageDTO = PageDTO.builder()
                .content(List.of(NotificacaoDTO.builder().build(),
                                NotificacaoDTO.builder().build())).build();
        when(notificacaoService.findAllNotificacoes(pageable)).thenReturn(notificacaoPageDTO);

        // Act
        ResponseEntity<?> response = notificacaoController.listar(pageable);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(notificacaoPageDTO, response.getBody());
    }

    @Test
    void testExcluirNotificacao() {
        // Arrange
        Long id = 1L;
        when(notificacaoService.excluir(id)).thenReturn(true);

        // Act
        ResponseEntity<?> response = notificacaoController.excluir(id);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Notificação excluida com sucesso!", response.getBody());
    }

    @Test
    void testExcluirNotificacao_Error() {
        // Arrange
        Long id = 1L;
        when(notificacaoService.excluir(id)).thenReturn(false);

        // Act
        ResponseEntity<?> response = notificacaoController.excluir(id);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Erro ao excluir notificação", response.getBody());
    }

    @Test
    void testIncluirNotificacao() {
        // Arrange
        NotificacaoDTO notificacaoDTO = new NotificacaoDTO();
        NotificacaoDTO notificacaoResponseDTO = new NotificacaoDTO();
        when(notificacaoService.incluir(notificacaoDTO)).thenReturn(notificacaoResponseDTO);

        // Act
        ResponseEntity<?> response = notificacaoController.incluir(notificacaoDTO);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(notificacaoResponseDTO, response.getBody());
    }
}