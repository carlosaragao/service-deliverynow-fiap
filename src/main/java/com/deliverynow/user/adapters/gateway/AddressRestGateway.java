package com.deliverynow.user.adapters.gateway;


import com.deliverynow.user.application.mapper.AddressMapper;
import com.deliverynow.user.infrastructure.rest.GetAddressRest;
import com.deliverynow.user.domain.entity.Address;
import com.deliverynow.user.domain.gateway.AddressGateway;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class AddressRestGateway implements AddressGateway {

    @Inject
    @RestClient
    GetAddressRest getAddressRest;
    @Inject
    AddressMapper addressMapper;

    @Override
    public Address getAddress(String postalCode) {
        var addressDto = getAddressRest.buscar(postalCode);
        return addressMapper.toDomain(addressDto);
    }
}
