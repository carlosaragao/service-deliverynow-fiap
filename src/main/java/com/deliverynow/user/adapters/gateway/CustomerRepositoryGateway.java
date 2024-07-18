package com.deliverynow.user.adapters.gateway;

import com.deliverynow.user.application.mapper.CustomerMapper;
import com.deliverynow.user.domain.entity.Customer;
import com.deliverynow.user.domain.gateway.CustomerGateway;
import com.deliverynow.user.infrastructure.repository.CustomerRepository;
import com.deliverynow.user.infrastructure.repository.entity.CustomerEntity;
import jakarta.enterprise.context.ApplicationScoped;
import org.bson.types.ObjectId;

import java.util.Optional;

@ApplicationScoped
public class CustomerRepositoryGateway implements CustomerGateway {


    CustomerRepository customerRepository;
    CustomerMapper customerMapper;

    public CustomerRepositoryGateway(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public void saveCustomer(Customer client) {
        customerRepository.persist(customerMapper.userToUserEntity(client));
    }

    @Override
    public Optional<Customer> getCustomerByDocument(String document) {
        var userByDocument = customerRepository.getUserByDocument(document);
        return userByDocument.map(user -> customerMapper.toDomain(user));
    }

    @Override
    public Customer getCustomerById(String customerId) {
        var customerEntity = customerRepository.findByIdOptional(new ObjectId(customerId));
        return customerEntity
                .map(customer -> customerMapper.toDomain(customer))
                .orElse(new Customer());
    }
}

