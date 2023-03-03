package com.betfair.interviews.cbotiza.ticketpricing.service;

import com.betfair.interviews.cbotiza.ticketpricing.domain.Transaction;
import com.betfair.interviews.cbotiza.ticketpricing.domain.TransactionTotals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketPricingService {

    @Autowired
    private AgeBasedTicketTypeRetriever ticketTypeRetriever;

    @Autowired
    private UnitPriceProvider unitPriceProvider;

    @Autowired(required = false)
    private BulkDiscountsProvider bulkDiscountsProvider = BulkDiscountsProvider.noDiscounts();

    public TransactionTotals totalsFor(Transaction transaction) {
        TransactionTotals totals = new TransactionTotals(transaction.getTransactionId());

        transaction.agesAsStream().mapToObj(ticketTypeRetriever::ticketTypeByAge).forEach(totals::update);

        updatePricesForTypes(totals);
        bulkDiscountsProvider.applyBulkDiscounts(totals);

        return totals.updateTotalCost();
    }

    private void updatePricesForTypes(TransactionTotals totals) {
        totals.getTotalsByType().forEach((ticketType, ticketTypeTotals) -> ticketTypeTotals.updateTotalForUnitPrice(unitPriceProvider.unitPrice(ticketType)));
    }
}
