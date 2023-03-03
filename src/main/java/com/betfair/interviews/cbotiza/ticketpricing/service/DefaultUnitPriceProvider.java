package com.betfair.interviews.cbotiza.ticketpricing.service;

import com.betfair.interviews.cbotiza.ticketpricing.domain.TicketType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultUnitPriceProvider implements UnitPriceProvider {

    @Autowired
    private UnitPrices unitPrices;

    @Override
    public double unitPrice(TicketType ticketType) {
        return unitPrices.price(ticketType);
    }
}
