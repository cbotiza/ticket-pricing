package com.betfair.interviews.cbotiza.ticketpricing.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * Total price for a set of tickets of the same type.
 * Prices are expressed as double, no real need for BigDecimal was identified.
 */
@Getter
@RequiredArgsConstructor
public class TicketTypeTotals {

    private int quantity;

    private final TicketType ticketType;

    @Setter
    private double totalPrice;

    public void newTicket() {
        quantity++;
    }

    public void updateTotalForUnitPrice(double unitPrice) {
        totalPrice = quantity * unitPrice;
    }
}
