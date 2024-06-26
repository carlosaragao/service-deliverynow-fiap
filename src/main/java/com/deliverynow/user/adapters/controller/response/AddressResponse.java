package com.deliverynow.user.adapters.controller.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressResponse {
        @Schema(description = "User postal code", example = "01311-000")
        private String postalCode;
        @Schema(description = "User street address", example = "Avenida Paulista")
        private String street;
        @Schema(description = "User state", example = "SP")
        private String state;
        @Schema(description = "User city", example = "Sao Paulo")
        private String city;
        @Schema(description = "User number address", example = "179")
        private String numberAddress;
}
