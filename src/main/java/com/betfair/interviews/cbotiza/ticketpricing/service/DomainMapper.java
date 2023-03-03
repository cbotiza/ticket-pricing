package com.betfair.interviews.cbotiza.ticketpricing.service;

import com.betfair.interviews.cbotiza.ticketpricing.api.model.Customer;
import com.betfair.interviews.cbotiza.ticketpricing.api.model.PricingTotals;
import com.betfair.interviews.cbotiza.ticketpricing.api.model.TransactionPricingRequest;
import com.betfair.interviews.cbotiza.ticketpricing.api.model.TransactionPricingResponse;
import com.betfair.interviews.cbotiza.ticketpricing.domain.Transaction;
import com.betfair.interviews.cbotiza.ticketpricing.domain.TransactionTotals;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DomainMapper {
    public static Transaction fromRequest(TransactionPricingRequest transactionPricingRequest) {

        List<Integer> ages = transactionPricingRequest.getCustomers().stream().map(Customer::age).toList();

        return new Transaction(transactionPricingRequest.getTransactionId(), ages);
    }

    public static TransactionPricingResponse toResponse(TransactionTotals totals) {
        Map<String, PricingTotals> totalsByType = new HashMap<>();
        totals.getTotalsByType().forEach((ticketType, ticketTypeTotals) -> totalsByType.put(ticketType.name(),
                new PricingTotals(ticketType, ticketTypeTotals.getQuantity(), ticketTypeTotals.getTotalPrice())));
        return new TransactionPricingResponse(totals.getTransactionId(), totalsByType, totals.getTotalCost());
    }
}
