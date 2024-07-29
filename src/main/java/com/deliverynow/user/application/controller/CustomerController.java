package com.deliverynow.user.application.controller;

import com.deliverynow.user.adapters.controller.request.CustomerRequest;
import com.deliverynow.user.adapters.controller.response.CustomerResponse;

public interface CustomerController {

    void insertCustomer(CustomerRequest customerRequest);

    CustomerResponse getCustomer(String document);

    String getCustomerSession(String document);
}
