package com.deliverynow.user.infrastructure.repository.entity;

import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@MongoEntity(collection="customer")
public class CustomerEntity {

    private String sessionId;
    private String name;
    private String phone;
    private String email;
    private String document;
    private String documentType;
    private String password;
    private Boolean allowNotification;
    private AddressEntity address;

}