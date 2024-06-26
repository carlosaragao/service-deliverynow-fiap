package com.deliverynow.user.infrastructure.repository;

import com.deliverynow.user.infrastructure.repository.entity.CustomerEntity;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;

@ApplicationScoped
public class CustomerRepository implements PanacheMongoRepository<CustomerEntity> {

    public Optional<CustomerEntity> getUserByDocument(String document){
        return find("document", document).firstResultOptional();
    }
}
