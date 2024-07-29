package com.deliverynow.user.application.usecase.impl;

import com.deliverynow.user.adapters.controller.request.CustomerRequest;
import com.deliverynow.user.application.exception.CustomerException;
import com.deliverynow.user.application.presenter.CustomerPresenter;
import com.deliverynow.user.application.usecase.InsertCustomerUseCase;
import com.deliverynow.user.domain.gateway.AddressGateway;
import com.deliverynow.user.domain.gateway.CustomerGateway;

public class InsertCustomerUseCaseImpl implements InsertCustomerUseCase {



    private final AddressGateway addressGateway;
    private final CustomerGateway customerGateway;
    private final CustomerPresenter customerPresenter;

    public InsertCustomerUseCaseImpl(AddressGateway addressGateway, CustomerGateway customerGateway, CustomerPresenter customerPresenter) {
        this.addressGateway = addressGateway;
        this.customerGateway = customerGateway;
        this.customerPresenter = customerPresenter;
    }

    @Override
    public void insertUser(CustomerRequest customerRequest) {

        var customer = customerGateway.getCustomerByDocument(customerRequest.document());
        if (customer.isEmpty()) {
            var address = addressGateway.getAddress(customerRequest.postalCode());
            address.setNumberAddress(customerRequest.numberAddress());
            var newCustomer = customerPresenter.requestToDomain(customerRequest, address);
            customerGateway.saveCustomer(newCustomer);
        } else {
            throw new CustomerException("User already exists with the informed document");
        }
    }
}
