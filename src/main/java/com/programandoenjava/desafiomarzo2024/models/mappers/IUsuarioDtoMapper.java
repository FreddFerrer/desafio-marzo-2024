package com.programandoenjava.desafiomarzo2024.models.mappers;

import com.programandoenjava.desafiomarzo2024.models.dtos.UsuarioDto;
import com.programandoenjava.desafiomarzo2024.models.dtos.request.CreateUserDto;
import com.programandoenjava.desafiomarzo2024.models.entities.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IUsuarioDtoMapper {

    UsuarioDto toDto(Usuario usuario);

    Usuario toEntity(CreateUserDto usuario);
}
