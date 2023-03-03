package com.betfair.interviews.cbotiza.ticketpricing.service;

import com.betfair.interviews.cbotiza.ticketpricing.domain.TicketType;

@FunctionalInterface
public interface AgeBasedTicketTypeRetriever {

    TicketType ticketTypeByAge(int customerAge);

}
