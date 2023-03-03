package com.betfair.interviews.cbotiza.ticketpricing.service;

import com.betfair.interviews.cbotiza.ticketpricing.domain.TicketType;

/**
 * Supplies prices for a single unit (ticket)
 */
@FunctionalInterface
public interface UnitPriceProvider {

    /**
     * @param ticketType
     * @return the unitary price of the a ticket of the given type, before bulk discounts, if any, are applied
     */
    double unitPrice(TicketType ticketType);
}
