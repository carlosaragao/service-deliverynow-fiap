package com.deliverynow.order.adapters.api.exception;

import com.deliverynow.order.application.exception.OrderException;
import com.deliverynow.user.adapters.controller.response.BaseResponse;
import com.deliverynow.user.adapters.controller.response.ErrorResponse;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ExceptionOrderHandle implements ExceptionMapper<OrderException> {

    @Override
    public Response toResponse(OrderException e) {

        var errorResponse = new ErrorResponse();
        errorResponse.setCode("400");
        errorResponse.setMenssage(e.getMessage());

        return Response.status(Response.Status.BAD_REQUEST).entity(new BaseResponse<>(errorResponse)).build();
    }
}
