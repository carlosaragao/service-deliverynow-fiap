package com.deliverynow.user.application.presenter;

import com.deliverynow.user.domain.entity.Address;
import com.deliverynow.user.infrastructure.rest.dto.AddressDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "jakarta", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AddressPresenter {

    @Mapping(target = "numberAddress", ignore = true)
    Address toDomain(AddressDto addressDto);
}
