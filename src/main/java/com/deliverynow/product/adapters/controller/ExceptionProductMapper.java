package com.deliverynow.product.adapters.controller;


import com.deliverynow.product.adapters.controller.response.BaseResponse;
import com.deliverynow.product.adapters.controller.response.ErrorResponse;
import com.deliverynow.product.application.exception.ProductException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ExceptionProductMapper implements ExceptionMapper<ProductException> {

    @Override
    public Response toResponse(ProductException e) {

        var errorResponse = new ErrorResponse();
        errorResponse.setCode("400");
        errorResponse.setMenssage(e.getMessage());

        return Response.status(Response.Status.BAD_REQUEST).entity(new BaseResponse<>(errorResponse)).build();
    }
}
