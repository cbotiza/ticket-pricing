package com.betfair.interviews.cbotiza.ticketpricing.service;

import com.betfair.interviews.cbotiza.ticketpricing.domain.TicketType;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

/**
 * Implementation based on intervals of age.
 * TODO make it more configurable, for example read interval boundaries from configuration,
 * or a specific service
 */
@Component
public class DefaultAgeBasedTicketType implements AgeBasedTicketTypeRetriever {

    private int teenLowerThreshold = 11;

    private int teenUpperThreshold = 18;

    private int adultUpperThreshold = 65;

    @PostConstruct
    public void validateBoundaries() {
        if (teenUpperThreshold <= teenLowerThreshold) {
            throw new IllegalStateException("Teen category upper threshold must be > lower threshold. " + teenUpperThreshold + " is not > " + teenLowerThreshold);
        }

        if (adultUpperThreshold <= teenUpperThreshold) {
            throw new IllegalStateException("Adult upper threshold must be > teen upper threshold. " + adultUpperThreshold + " is not > " + teenUpperThreshold);
        }
    }

    @Override
    public TicketType ticketTypeByAge(int customerAge) {
        if (customerAge < teenLowerThreshold) {
            return TicketType.CHILDREN;
        }

        if (customerAge <= teenUpperThreshold) {
            return TicketType.TEEN;
        }

        if (customerAge <= adultUpperThreshold) {
            return TicketType.ADULT;
        }

        return TicketType.SENIOR;
    }
}
