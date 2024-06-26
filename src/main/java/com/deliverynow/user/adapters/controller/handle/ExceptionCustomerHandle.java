package com.deliverynow.user.adapters.controller.handle;

import com.deliverynow.user.adapters.controller.response.BaseResponse;
import com.deliverynow.user.adapters.controller.response.ErrorResponse;
import com.deliverynow.user.application.exception.CustomerException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ExceptionCustomerHandle implements ExceptionMapper<CustomerException> {

    @Override
    public Response toResponse(CustomerException e) {

        var errorResponse = new ErrorResponse();
        errorResponse.setCode("400");
        errorResponse.setMenssage(e.getMessage());

        return Response.status(Response.Status.BAD_REQUEST).entity(new BaseResponse<>(errorResponse)).build();
    }
}
