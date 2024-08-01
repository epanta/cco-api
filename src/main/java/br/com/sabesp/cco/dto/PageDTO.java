package br.com.sabesp.cco.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PageDTO {
    private List<NotificacaoDTO> content;
    private int pageNumber;
    private int pageSize;
    private long totalElements;
    private int totalPages;
}
