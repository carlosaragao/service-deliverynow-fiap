package com.deliverynow.user.application.presenter;

import com.deliverynow.user.adapters.controller.request.CustomerRequest;
import com.deliverynow.user.domain.entity.Address;
import com.deliverynow.user.domain.entity.Customer;
import com.deliverynow.user.infrastructure.repository.entity.CustomerEntity;
import com.deliverynow.user.adapters.controller.response.CustomerResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "jakarta", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CustomerPresenter {

    CustomerResponse domainToResponse(Customer customer);

    @Mapping(target = "sessionId", ignore = true)
    CustomerEntity userToUserEntity(Customer customer);
    Customer toDomain(CustomerEntity user);

    @Mapping(source = "customer.name", target = "name")
    @Mapping(source = "customer.phone", target = "phone")
    @Mapping(source = "customer.email", target = "email")
    @Mapping(source = "customer.documentType", target = "documentType")
    @Mapping(source = "customer.allowNotification", target = "allowNotification")
    @Mapping(source = "customer.password", target = "password")
    @Mapping(source = "address.postalCode", target = "address.postalCode")
    @Mapping(source = "address.street", target = "address.street")
    @Mapping(source = "address.state", target = "address.state")
    @Mapping(source = "address.city", target = "address.city")
    @Mapping(source = "address.numberAddress", target = "address.numberAddress")
    Customer requestToDomain(CustomerRequest customer, Address address);
}
