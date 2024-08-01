package br.com.sabesp.cco.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

@Getter
@AllArgsConstructor
public enum TipoAcaoEnum {

    PLANILHA_CCO_DESBLOQUEADA("Planilha CCO desbloqueada"),
    PLANILHA_CCO_DESBLOQUEADA_MANUAL("Planilha CCO bloqueada manual"),
    IMPORTACAO_MANUAL("Importação manual"),
    ERRO_IMPORTACAO_AUTOMATICA("Erro de importação automática"),
    ERRO_COMUNICACAO_HISTORIAN("Erro de comunicação com historian"),
    EDITAR_PLANILHA_CCO("Editar planilha CCO"),
    ADICIONAR("Adicionar");

    private final String descricao;

    public static TipoAcaoEnum from(String perfil) {
        if (!StringUtils.isBlank(perfil)) {

            for (TipoAcaoEnum tipoAcaoEnum : TipoAcaoEnum.values()) {
                if (tipoAcaoEnum.descricao.equals(perfil)) {
                    return tipoAcaoEnum;
                }
            }
        }
        return null;
    }
}
