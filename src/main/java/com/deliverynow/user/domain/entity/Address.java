package com.deliverynow.user.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    private String postalCode;
    private String street;
    private String state;
    private String city;
    private String numberAddress;
}
