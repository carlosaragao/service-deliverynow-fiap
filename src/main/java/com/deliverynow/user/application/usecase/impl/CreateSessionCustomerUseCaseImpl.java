package com.deliverynow.user.application.usecase.impl;

import com.deliverynow.user.application.usecase.CreateSessionCustomerUseCase;
import com.deliverynow.user.domain.gateway.CustomerGateway;

import java.util.UUID;

public class CreateSessionCustomerUseCaseImpl implements CreateSessionCustomerUseCase {

    CustomerGateway customerGateway;

    public CreateSessionCustomerUseCaseImpl(CustomerGateway customerGateway) {
        this.customerGateway = customerGateway;
    }

    @Override
    public String createSession(String document) {

        var sessionId = UUID.randomUUID().toString();
        if (document != null) {
            customerGateway.updateCustomer(document, sessionId);
        }
        return sessionId;
    }
}
