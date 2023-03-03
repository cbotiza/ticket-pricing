package com.betfair.interviews.cbotiza.ticketpricing.service;

import com.betfair.interviews.cbotiza.ticketpricing.domain.TicketType;
import com.betfair.interviews.cbotiza.ticketpricing.domain.TicketTypeTotals;
import com.betfair.interviews.cbotiza.ticketpricing.domain.TransactionTotals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ChildrenGroupDiscountTest {

    private ChildrenGroupDiscount discount;

    @BeforeEach
    public void setUp() {
        discount = new ChildrenGroupDiscount();
        discount.setPercent(20);
        discount.setThreshold(2);
    }

    @Test
    public void shouldNotApplyIfBelowThreshold() {
        TransactionTotals totals = new TransactionTotals(35235);
        totals.update(TicketType.CHILDREN);
        TicketTypeTotals childrenTotals = totals.getTotalsByType().get(TicketType.CHILDREN);

        assertNotNull(childrenTotals);
        assertEquals(1, childrenTotals.getQuantity());

        childrenTotals.updateTotalForUnitPrice(5D);
        assertEquals(5D, childrenTotals.getTotalPrice());

        discount.applyBulkDiscounts(totals);

        assertEquals(35235, totals.getTransactionId());
        // verify discount not applied
        assertEquals(5D, childrenTotals.getTotalPrice());
    }

    @Test
    public void shouldNotApplyIfNoChildrenTickets() {
        TransactionTotals totals = new TransactionTotals(35236);
        totals.update(TicketType.ADULT);

        TicketTypeTotals adultTotals = totals.getTotalsByType().get(TicketType.ADULT);
        TicketTypeTotals childrenTotals = totals.getTotalsByType().get(TicketType.CHILDREN);

        assertNull(childrenTotals);
        assertNotNull(adultTotals);

        adultTotals.updateTotalForUnitPrice(25);

        assertEquals(25D, adultTotals.getTotalPrice());

        discount.applyBulkDiscounts(totals);

        assertEquals(35236, totals.getTransactionId());
        childrenTotals = totals.getTotalsByType().get(TicketType.CHILDREN);
        // verify discount not applied
        assertNull(childrenTotals);

        adultTotals = totals.getTotalsByType().get(TicketType.ADULT);
        assertEquals(25D, adultTotals.getTotalPrice());
    }

    @Test
    public void shouldApplyConfiguredDiscountIfThresholdReached() {

        TransactionTotals totals = new TransactionTotals(35236);
        totals.update(TicketType.ADULT);
        totals.update(TicketType.CHILDREN);
        totals.update(TicketType.CHILDREN);

        TicketTypeTotals childTotals = totals.getTotalsByType().get(TicketType.CHILDREN);

        assertNotNull(childTotals);
        assertEquals(2, childTotals.getQuantity());

        childTotals.updateTotalForUnitPrice(10D);
        assertEquals(20D, childTotals.getTotalPrice());//not discounted

        discount.applyBulkDiscounts(totals);
        assertEquals(16D, childTotals.getTotalPrice());
    }
}
