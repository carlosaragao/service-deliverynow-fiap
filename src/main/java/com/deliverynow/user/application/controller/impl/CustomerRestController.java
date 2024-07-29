package com.deliverynow.user.application.controller.impl;

import com.deliverynow.user.adapters.controller.request.CustomerRequest;
import com.deliverynow.user.adapters.controller.response.CustomerResponse;
import com.deliverynow.user.application.controller.CustomerController;
import com.deliverynow.user.application.presenter.CustomerPresenter;
import com.deliverynow.user.application.usecase.CreateSessionCustomerUseCase;
import com.deliverynow.user.application.usecase.GetCustomerByDocumentUseCase;
import com.deliverynow.user.application.usecase.InsertCustomerUseCase;

public class CustomerRestController implements CustomerController {

    InsertCustomerUseCase insertCustomerUseCase;
    GetCustomerByDocumentUseCase getCustomerByDocumentUseCase;
    CreateSessionCustomerUseCase createSessionCustomerUseCase;
    CustomerPresenter customerPresenter;

    public CustomerRestController(InsertCustomerUseCase insertCustomerUseCase, GetCustomerByDocumentUseCase getCustomerByDocumentUseCase, CreateSessionCustomerUseCase createSessionCustomerUseCase, CustomerPresenter customerPresenter) {
        this.insertCustomerUseCase = insertCustomerUseCase;
        this.getCustomerByDocumentUseCase = getCustomerByDocumentUseCase;
        this.createSessionCustomerUseCase = createSessionCustomerUseCase;
        this.customerPresenter = customerPresenter;
    }

    @Override
    public void insertCustomer(CustomerRequest customerRequest) {
        insertCustomerUseCase.insertUser(customerRequest);
    }

    @Override
    public CustomerResponse getCustomer(String document) {
        var customer = getCustomerByDocumentUseCase.getUserByDocument(document);
        return customerPresenter.domainToResponse(customer);
    }

    @Override
    public String getCustomerSession(String document) {
        return createSessionCustomerUseCase.createSession(document);
    }
}
