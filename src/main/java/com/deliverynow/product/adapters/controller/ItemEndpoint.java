package com.deliverynow.product.adapters.controller;

import com.deliverynow.product.adapters.controller.request.ItemRequest;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.resteasy.reactive.RestResponse;

@Path("/item")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Item controller", description = "Item operations")
public class ItemEndpoint {

    @POST
    @Path("select")
    @Operation(summary = "Select itens")
    public RestResponse<Void> insertProduct(@Valid ItemRequest itemRequest) {

        return RestResponse.ok();
    }
}
