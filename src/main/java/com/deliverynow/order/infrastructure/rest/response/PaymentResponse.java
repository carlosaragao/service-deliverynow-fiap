package com.deliverynow.order.infrastructure.rest.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentResponse {

    private Long id;
    private String status;
    private String externalReference;
    private String preferenceId;
    private List<Payment> payments;
    private String marketplace;
    private String notificationUrl;
    private String dateCreated;
    private String lastUpdated;
    private Object sponsorId;
    private Double shippingCost;
    private Double totalAmount;
    private String siteId;
    private Double paidAmount;
    private Double refundedAmount;
    private List<Item> items;
    private boolean cancelled;
    private String additionalInfo;
    private Object applicationId;
    private String orderStatus;
    private String clientId;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Payment {
        private Long id;
        private Double transactionAmount;
        private Double totalPaidAmount;
        private Double shippingCost;
        private String currencyId;
        private String status;
        private String statusDetail;
        private String operationType;
        private String dateApproved;
        private String dateCreated;
        private String lastModified;
        private Double amountRefunded;

        // Getters and Setters
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Item {
        private String id;
        private String categoryId;
        private String currencyId;
        private String description;
        private String pictureUrl;
        private String title;
        private Integer quantity;
        private Double unitPrice;

    }
}
