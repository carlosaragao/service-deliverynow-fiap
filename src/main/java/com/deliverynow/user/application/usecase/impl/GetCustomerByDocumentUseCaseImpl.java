package com.deliverynow.user.application.usecase.impl;

import com.deliverynow.user.application.exception.CustomerException;
import com.deliverynow.user.application.usecase.GetCustomerByDocumentUseCase;
import com.deliverynow.user.domain.entity.Customer;
import com.deliverynow.user.domain.gateway.CustomerGateway;

public class GetCustomerByDocumentUseCaseImpl implements GetCustomerByDocumentUseCase {

    CustomerGateway customerGateway;

    public GetCustomerByDocumentUseCaseImpl(CustomerGateway customerGateway) {
        this.customerGateway = customerGateway;
    }

    @Override
    public Customer getUserByDocument(String document) {
        var customerByDocument = customerGateway.getCustomerByDocument(document);
        return customerByDocument.orElseThrow(() -> new CustomerException("User not found with the document provided."));
    }
}
