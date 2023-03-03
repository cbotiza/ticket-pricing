package com.betfair.interviews.cbotiza.ticketpricing.api.model;

import lombok.Getter;

import java.util.Map;
import java.util.TreeMap;

@Getter
public class TransactionPricingResponse {
    private long transactionId;
    private Map<String, PricingTotals> tickets;
    private double totalPrice;

    public TransactionPricingResponse(long transactionId, Map<String, PricingTotals> tickets, double totalPrice) {
        this.transactionId = transactionId;
        this.totalPrice = totalPrice;
        this.tickets = new TreeMap<>(tickets);
    }
}
