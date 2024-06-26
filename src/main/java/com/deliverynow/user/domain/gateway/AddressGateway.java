package com.deliverynow.user.domain.gateway;


import com.deliverynow.user.domain.entity.Address;

public interface AddressGateway {

    Address getAddress(String postalCode);
}
