package com.deliverynow.user.application.mapper;

import com.deliverynow.user.domain.entity.Address;
import com.deliverynow.user.infrastructure.rest.dto.AddressDto;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "jakarta", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AddressMapper {

    Address toDomain(AddressDto addressDto);
}
