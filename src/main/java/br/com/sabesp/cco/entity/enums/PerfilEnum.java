package br.com.sabesp.cco.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

@Getter
@AllArgsConstructor
public enum PerfilEnum {

    ADMIN("Administrador"),
    COO("COO"),
    Centro("Centro"),
    Correcao("Correção"),
    ETA3("ETA 3"),
    ETA3_ADMIN("ETA 3 ADMIN"),
    GLOBAL("Global"),
    MASPP_GRAFICOS("MappsGraficos"),
    MASPP_LIMITES("MappsLimites"),
    NORTE("Norte"),
    PITOMETRIA("Pitometria"),
    SABOO("Saboo"),
    SUL("Sul");
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
