package com.betfair.interviews.cbotiza.ticketpricing.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Consumer;

@Getter
@RequiredArgsConstructor
public class TransactionTotals {

    private final long transactionId;

    private double totalCost;

    private final Map<TicketType, TicketTypeTotals> totalsByType = new EnumMap<>(TicketType.class);

    public void applyIfPresent(TicketType ticketType, Consumer<TicketTypeTotals> updater) {
        TicketTypeTotals totals = totalsByType.get(ticketType);
        if (totals != null) {
            updater.accept(totals);
        }
    }

    /**
     * React to a new ticket of the given type
     *
     * @param ticketType the type of the ticket to consider
     */
    public void update(TicketType ticketType) {
        TicketTypeTotals totals = totalsByType.computeIfAbsent(ticketType, TicketTypeTotals::new);

        totals.newTicket();
    }


    /**
     * Update total cost based on the (possibly discounted) totals for each type
     */
    public TransactionTotals updateTotalCost() {
        totalCost = totalsByType.values().stream().map(TicketTypeTotals::getTotalPrice).mapToDouble(Double::doubleValue).sum();
        return this;
    }
}
