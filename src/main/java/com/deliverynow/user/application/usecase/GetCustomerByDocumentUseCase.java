package com.deliverynow.user.application.usecase;

import com.deliverynow.user.adapters.controller.response.CustomerResponse;

public interface GetCustomerByDocumentUseCase {

    CustomerResponse getUserByDocument(String document);
}
