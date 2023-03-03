package com.betfair.interviews.cbotiza.ticketpricing.service;

import com.betfair.interviews.cbotiza.ticketpricing.domain.TransactionTotals;

@FunctionalInterface
public interface BulkDiscountsProvider {

    void applyBulkDiscounts(TransactionTotals totalsBeforeDiscounts);

    static BulkDiscountsProvider noDiscounts() {
        return totalsBeforeDiscounts -> {
        };
    }
}
