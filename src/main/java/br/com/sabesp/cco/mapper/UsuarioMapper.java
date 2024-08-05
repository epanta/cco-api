package br.com.sabesp.cco.mapper;

import br.com.sabesp.cco.dto.PageDTO;
import br.com.sabesp.cco.dto.UsuarioDTO;
import br.com.sabesp.cco.entity.Usuario;
import br.com.sabesp.cco.entity.enums.PerfilEnum;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.data.domain.Page;

import java.util.Date;
import java.util.List;

@Mapper(componentModel = "spring", imports = {PerfilEnum.class, Date.class})
public interface UsuarioMapper {

    UsuarioDTO toDTO(Usuario usuario);
    Usuario updateUsuarioFromDTO(UsuarioDTO usuarioDTO, @MappingTarget Usuario usuario);

    PageDTO toDto(Page<Usuario> usuarioPage);

    @IterableMapping(elementTargetType = UsuarioDTO.class)
    List<UsuarioDTO> usuariosToUsuariosDto(List<Usuario> usuarios);
    @Mapping(target = "perfil", expression = "java(PerfilEnum.from(dto.getPerfil()))")
    @Mapping(target = "dtCriacao", expression = "java(new Date())")
    @Mapping(target = "dtAtualizacao", expression = "java(new Date())")
    Usuario toEntity(UsuarioDTO dto);
}
