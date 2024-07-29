package com.deliverynow.user.adapters.gateway;

import com.deliverynow.user.application.presenter.CustomerPresenter;
import com.deliverynow.user.domain.entity.Customer;
import com.deliverynow.user.domain.gateway.CustomerGateway;
import com.deliverynow.user.infrastructure.repository.CustomerRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;

@ApplicationScoped
public class CustomerRepositoryGateway implements CustomerGateway {


    CustomerRepository customerRepository;
    CustomerPresenter customerPresenter;

    public CustomerRepositoryGateway(CustomerRepository customerRepository, CustomerPresenter customerPresenter) {
        this.customerRepository = customerRepository;
        this.customerPresenter = customerPresenter;
    }

    @Override
    public void saveCustomer(Customer client) {
        customerRepository.persist(customerPresenter.userToUserEntity(client));
    }

    @Override
    public void updateCustomer(String document, String sessionId) {
        customerRepository.update("sessionId", sessionId).where("document", document);
    }

    @Override
    public Optional<Customer> getCustomerByDocument(String document) {
        var userByDocument = customerRepository.getUserByDocument(document);
        return userByDocument.map(user -> customerPresenter.toDomain(user));
    }

    @Override
    public Optional<Customer> getCustomerById(String sessionId) {
        var customerEntity = customerRepository.getUserBySessionId(sessionId);
        return customerEntity
                .map(customer -> customerPresenter.toDomain(customer));
    }
}

