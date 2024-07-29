package com.deliverynow.user.infrastructure.config;

import com.deliverynow.user.application.usecase.impl.CreateSessionCustomerUseCaseImpl;
import com.deliverynow.user.application.usecase.impl.GetCustomerByDocumentUseCaseImpl;
import com.deliverynow.user.application.usecase.impl.InsertCustomerUseCaseImpl;
import com.deliverynow.user.application.presenter.CustomerPresenter;
import com.deliverynow.user.domain.gateway.AddressGateway;
import com.deliverynow.user.domain.gateway.CustomerGateway;
import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.inject.Default;

@Dependent
public class CustomerUsaCaseConfig {

    @Default
    public InsertCustomerUseCaseImpl insertCustomerUseCase(AddressGateway addressGateway, CustomerGateway customerGateway, CustomerPresenter customerPresenter) {
        return new InsertCustomerUseCaseImpl(addressGateway, customerGateway, customerPresenter);
    }

    @Default
    public GetCustomerByDocumentUseCaseImpl getCustomerByDocument(CustomerGateway customerGateway) {
        return new GetCustomerByDocumentUseCaseImpl(customerGateway);
    }

    @Default
    public CreateSessionCustomerUseCaseImpl createSessionCustomerUseCase(CustomerGateway customerGateway) {
        return new CreateSessionCustomerUseCaseImpl(customerGateway);
    }
}
