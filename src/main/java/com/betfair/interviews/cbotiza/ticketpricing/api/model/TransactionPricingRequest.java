package com.betfair.interviews.cbotiza.ticketpricing.api.model;

import jakarta.validation.constraints.Min;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class TransactionPricingRequest {

    @Min(0)
    private long transactionId;

    private List<Customer> customers;
}
