package com.project.banking_api.src.mappers;

import com.project.banking_api.src.entities.Transfer;
import com.project.banking_api.src.infra.models.TransferModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TransferMapper {

    @Mapping(source = "accountSent.id", target = "accountSent")
    @Mapping(source = "accountReceived.id", target = "accountReceived")
    @Mapping(source = "dateBalanceTransfer", target = "dateBalanceTransfer")
    Transfer toEntity(TransferModel transferModel);

    @Mapping(target = "accountSent", ignore = true)
    @Mapping(target = "accountReceived", ignore = true)
    @Mapping(target = "dateBalanceTransfer", ignore = true)
    TransferModel toModel(Transfer transfer);
}

