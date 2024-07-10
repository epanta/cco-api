package br.com.sabesp.cco.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

@Getter
@AllArgsConstructor
public enum PerfilEnum {

    ADMIN("Administrador"),
    REPRESENTANTE("Representante"),
    Consulta("Consulta");

    private final String descricao;

    public static PerfilEnum from(String perfil){
        if(!StringUtils.isBlank(perfil)) {

            for (PerfilEnum perfilEnum : PerfilEnum.values()) {
                if (perfilEnum.descricao.equals(perfil)) {
                    return perfilEnum;
                }
            }
        }
        return null;
    }

    public boolean in(PerfilEnum... perfis){
        for (PerfilEnum tipo: perfis) {
            if(this.equals(tipo)){
                return true;
            }
        }
        return false;
    }
}
