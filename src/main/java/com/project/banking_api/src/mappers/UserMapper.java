package com.project.banking_api.src.mappers;

import com.project.banking_api.src.dtos.RegisterRequestDTO;
import com.project.banking_api.src.entities.User;
import com.project.banking_api.src.infra.models.UserModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "role", ignore = true)
    User toEntity(RegisterRequestDTO registerRequestDTO);

    UserModel toModel(User user);

    User toEntity(UserModel userModel);

}
