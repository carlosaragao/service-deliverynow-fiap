package com.deliverynow.user.domain.gateway;


import com.deliverynow.user.domain.entity.Customer;

import java.util.Optional;

public interface CustomerGateway {

    void saveCustomer(Customer client);

    Optional<Customer> getCustomerByDocument(String document);
    Customer getCustomerById(String customerId);
}
