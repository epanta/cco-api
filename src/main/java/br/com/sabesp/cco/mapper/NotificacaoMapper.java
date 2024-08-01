package br.com.sabesp.cco.mapper;

import br.com.sabesp.cco.dto.NotificacaoDTO;
import br.com.sabesp.cco.dto.PageDTO;
import br.com.sabesp.cco.entity.Notificacao;
import br.com.sabesp.cco.entity.enums.TipoAcaoEnum;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Mapper(componentModel = "spring", imports = {TipoAcaoEnum.class, DateTimeFormatter.class})
public interface NotificacaoMapper {

    DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    PageDTO toDto(Page notificacao);

    @IterableMapping(elementTargetType = NotificacaoDTO.class)
    List<NotificacaoDTO> notificacoesToNotificacaoDtos(List<Notificacao> notificacao);

    @Mapping(target = "funcionalidade", expression = "java(notificacao.getFuncionalidade().getDescricao())")
    @Mapping(target = "dataHora", expression = "java(notificacao.getDataHora().format(DATE_TIME_FORMATTER))")
    @Mapping(target = "dataLiberacao", expression = "java(notificacao.getDataLiberacao().format(DATE_FORMATTER))")
    @Mapping(target = "dataPlanilha", expression = "java(notificacao.getDataPlanilha().format(DATE_FORMATTER))")
    @Mapping(target = "ultimoAjuste", expression = "java(notificacao.getUltimoAjuste().format(DATE_TIME_FORMATTER))")
    @Mapping(target = "dataFinal", expression = "java(notificacao.getDataFinal().format(DATE_FORMATTER))")
    NotificacaoDTO toDto(Notificacao notificacao);

    @Mapping(target = "funcionalidade", expression = "java(TipoAcaoEnum.from(notificacaoDTO.getFuncionalidade()))")
    Notificacao toModel(NotificacaoDTO notificacaoDTO);
}
