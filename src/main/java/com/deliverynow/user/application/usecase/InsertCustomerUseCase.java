package com.deliverynow.user.application.usecase;

import com.deliverynow.user.adapters.controller.request.CustomerRequest;

public interface InsertCustomerUseCase {

    void insertUser(CustomerRequest user);
}
