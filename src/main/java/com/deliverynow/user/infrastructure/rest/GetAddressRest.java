package com.deliverynow.user.infrastructure.rest;

import com.deliverynow.user.infrastructure.rest.dto.AddressDto;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/ws")
@RegisterRestClient(baseUri = "https://viacep.com.br")
public interface GetAddressRest {

    @GET
    @Path("/{cep}/json")
    AddressDto buscar(@PathParam("cep") String postCode);
}
