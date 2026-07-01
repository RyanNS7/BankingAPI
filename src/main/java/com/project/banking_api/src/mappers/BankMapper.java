package com.project.banking_api.src.mappers;

import com.project.banking_api.src.entities.BankAccount;
import com.project.banking_api.src.infra.models.BankAccountModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BankMapper {

    @Mapping(source = "user.id", target = "userID")
    BankAccount toEntity(BankAccountModel bankAccountModel);

    @Mapping(target = "user", ignore = true)
    BankAccountModel toModel(BankAccount bankAccount);

}
