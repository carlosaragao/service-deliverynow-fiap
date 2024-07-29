package com.deliverynow.user.application.usecase;

import com.deliverynow.user.domain.entity.Customer;

public interface GetCustomerByDocumentUseCase {

    Customer getUserByDocument(String document);
}
