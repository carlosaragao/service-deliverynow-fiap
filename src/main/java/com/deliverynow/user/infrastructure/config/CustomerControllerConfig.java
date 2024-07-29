package com.deliverynow.user.infrastructure.config;

import com.deliverynow.user.application.controller.impl.CustomerRestController;
import com.deliverynow.user.application.presenter.CustomerPresenter;
import com.deliverynow.user.application.usecase.CreateSessionCustomerUseCase;
import com.deliverynow.user.application.usecase.GetCustomerByDocumentUseCase;
import com.deliverynow.user.application.usecase.InsertCustomerUseCase;
import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.inject.Default;

@Dependent
public class CustomerControllerConfig {

    @Default
    public CustomerRestController customerRestController(InsertCustomerUseCase insertCustomerUseCase, GetCustomerByDocumentUseCase getCustomerByDocumentUseCase, CreateSessionCustomerUseCase createSessionCustomerUseCase, CustomerPresenter customerPresenter) {
        return new CustomerRestController(insertCustomerUseCase, getCustomerByDocumentUseCase, createSessionCustomerUseCase, customerPresenter);
    }
}
