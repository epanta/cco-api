package br.com.sabesp.cco.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

@Getter
@AllArgsConstructor
public enum SituacaoEnum {

    PENDENTE("Pendente"),
    RECEBIDO("Recebido"),
    FINALIZADO("Finalizado");

    private final String descricao;

    public static SituacaoEnum from(String perfil){
        if(!StringUtils.isBlank(perfil)) {

            for (SituacaoEnum situacao : SituacaoEnum.values()) {
                if (situacao.descricao.equals(perfil)) {
                    return situacao;
                }
            }
        }
        return null;
    }
}
