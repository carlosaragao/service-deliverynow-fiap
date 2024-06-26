package com.deliverynow.user.application.usecase.impl;

import com.deliverynow.user.adapters.controller.response.CustomerResponse;
import com.deliverynow.user.application.exception.CustomerException;
import com.deliverynow.user.application.usecase.GetCustomerByDocumentUseCase;
import com.deliverynow.user.application.mapper.CustomerMapper;
import com.deliverynow.user.domain.gateway.CustomerGateway;

public class GetCustomerByDocumentUseCaseImpl implements GetCustomerByDocumentUseCase {

    CustomerGateway customerGateway;
    CustomerMapper customerMapper;

    public GetCustomerByDocumentUseCaseImpl(CustomerGateway customerGateway, CustomerMapper customerMapper) {
        this.customerGateway = customerGateway;
        this.customerMapper = customerMapper;
    }

    @Override
    public CustomerResponse getUserByDocument(String document) {
        var customerByDocument = customerGateway.getCustomerByDocument(document);
        return customerByDocument.map(customer ->  customerMapper.domainToResponse(customer))
                .orElseThrow(() -> new CustomerException("User not found with the document provided."));
    }
}
