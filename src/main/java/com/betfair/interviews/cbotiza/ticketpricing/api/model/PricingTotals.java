package com.betfair.interviews.cbotiza.ticketpricing.api.model;

import com.betfair.interviews.cbotiza.ticketpricing.domain.TicketType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PricingTotals {
    private TicketType ticketType;

    private int quantity;

    private double totalCost;
}
