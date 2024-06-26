package com.deliverynow.user.adapters.controller;

import com.deliverynow.user.adapters.controller.request.CustomerRequest;
import com.deliverynow.user.adapters.controller.response.BaseResponse;
import com.deliverynow.user.adapters.controller.response.CustomerResponse;
import com.deliverynow.user.application.usecase.InsertCustomerUseCase;
import com.deliverynow.user.application.usecase.GetCustomerByDocumentUseCase;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.resteasy.reactive.RestResponse;

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "User controller", description = "User operations")
public class CustomerEndpoint {

    InsertCustomerUseCase insertCustomerUseCase;
    GetCustomerByDocumentUseCase getCustomerByDocumentUseCase;

    public CustomerEndpoint(InsertCustomerUseCase insertCustomerUseCase, GetCustomerByDocumentUseCase getCustomerByDocumentUseCase) {
        this.insertCustomerUseCase = insertCustomerUseCase;
        this.getCustomerByDocumentUseCase = getCustomerByDocumentUseCase;
    }

    @POST
    @Operation(summary = "Inserir um novo cliente")
    public RestResponse<Void> insertClient(@Valid CustomerRequest customerRequest) {
        insertCustomerUseCase.insertUser(customerRequest);
        return RestResponse.ok();
    }

    @GET
    @Operation(summary = "Buscar cliente por documento")
    public RestResponse<BaseResponse<CustomerResponse>> getClient(@QueryParam("document") String document) {
        var cLientResponse = getCustomerByDocumentUseCase.getUserByDocument(document);
        return RestResponse.ok(new BaseResponse<>(cLientResponse));
    }
}
