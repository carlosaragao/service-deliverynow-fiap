package com.deliverynow.user.domain.gateway;


import com.deliverynow.user.domain.entity.Customer;

import java.util.Optional;

public interface CustomerGateway {

    void saveCustomer(Customer client);
    void updateCustomer(String document, String sessionId);

    Optional<Customer> getCustomerByDocument(String document);
    Optional<Customer> getCustomerById(String customerId);
}
