package com.deliverynow.user.infrastructure.config;

import com.deliverynow.user.application.usecase.impl.GetCustomerByDocumentUseCaseImpl;
import com.deliverynow.user.application.usecase.impl.InsertCustomerUseCaseImpl;
import com.deliverynow.user.application.mapper.CustomerMapper;
import com.deliverynow.user.domain.gateway.AddressGateway;
import com.deliverynow.user.domain.gateway.CustomerGateway;
import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.inject.Default;

@Dependent
public class CustomerUsaCaseConfig {

    @Default
    public InsertCustomerUseCaseImpl insertCustomerUseCase(AddressGateway addressGateway, CustomerGateway customerGateway, CustomerMapper customerMapper) {
        return new InsertCustomerUseCaseImpl(addressGateway, customerGateway, customerMapper);
    }

    @Default
    public GetCustomerByDocumentUseCaseImpl getCustomerByDocument(CustomerGateway customerGateway, CustomerMapper customerMapper) {
        return new GetCustomerByDocumentUseCaseImpl(customerGateway, customerMapper);
    }
}
