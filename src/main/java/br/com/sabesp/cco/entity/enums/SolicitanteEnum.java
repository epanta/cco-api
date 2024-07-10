package br.com.sabesp.cco.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

@Getter
@AllArgsConstructor
public enum SolicitanteEnum {

    OUVIDORIA("OUVIDORIA"),
    ARSESP("ARSESP"),
    PA("PA"),
    PROCON("PROCON"),
    SABESP("SABESP");

    private final String descricao;

    public static SolicitanteEnum from(String perfil){
        if(!StringUtils.isBlank(perfil)) {

            for (SolicitanteEnum solicitante : SolicitanteEnum.values()) {
                if (solicitante.descricao.equals(perfil)) {
                    return solicitante;
                }
            }
        }
        return null;
    }
}
