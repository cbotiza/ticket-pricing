package com.betfair.interviews.cbotiza.ticketpricing.service;

import com.betfair.interviews.cbotiza.ticketpricing.domain.TicketType;
import com.betfair.interviews.cbotiza.ticketpricing.domain.TicketTypeTotals;
import com.betfair.interviews.cbotiza.ticketpricing.domain.TransactionTotals;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Setter
public class ChildrenGroupDiscount implements BulkDiscountsProvider {

    private int threshold;

    private int percent;

    @Override
    public void applyBulkDiscounts(TransactionTotals totalsBeforeDiscounts) {
        totalsBeforeDiscounts.applyIfPresent(TicketType.CHILDREN, this::discount);
    }

    private void discount(TicketTypeTotals childrenTotals) {
        if (childrenTotals.getQuantity() >= threshold) {
            log.info("Applying discount for {} children: {}", childrenTotals.getQuantity(), percent);
            childrenTotals.setTotalPrice((1 - (double) percent / 100D) * childrenTotals.getTotalPrice());
        }
    }
}
