package com.deliverynow.product.adapters.controller;

import com.deliverynow.product.adapters.controller.request.ItemRequest;
import com.deliverynow.product.application.controller.ItemController;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.resteasy.reactive.RestResponse;

@Path("/item")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Item controller", description = "Item operations")
public class ItemRestController {

    ItemController itemController;

    public ItemRestController(ItemController itemController) {
        this.itemController = itemController;
    }

    @POST
    @Path("select")
    @Operation(summary = "Select itens")
    public RestResponse<Void> insertProduct(@Valid ItemRequest itemRequest) {
        itemController.selectProduct(itemRequest);
        return RestResponse.ok();
    }

    @DELETE
    @Path("remove/{itemId}")
    @Operation(summary = "Select itens")
    public RestResponse<Void> insertProduct(@PathParam("itemId") String id) {
        itemController.removeItem(id);
        return RestResponse.noContent();
    }
}
