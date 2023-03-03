package com.betfair.interviews.cbotiza.ticketpricing.service;

import com.betfair.interviews.cbotiza.ticketpricing.domain.TicketType;
import jakarta.annotation.PostConstruct;
import lombok.Setter;

import java.util.EnumMap;
import java.util.Map;

@Setter
public class UnitPrices {

    private double adult;

    private double teen;

    private double children;

    private int seniorDiscountPercentage = 30;

    private final Map<TicketType, Double> unitPrices = new EnumMap<>(TicketType.class);

    @PostConstruct
    public void validate() {
        if (seniorDiscountPercentage < 0 || seniorDiscountPercentage > 100) {
            throw new IllegalStateException("Expected discount percentage to be between 0 - 100, got " + seniorDiscountPercentage);
        }
        validate(adult, "adult");
        validate(teen, "teen");
        validate(children, "children");

        unitPrices.put(TicketType.ADULT, adult);
        unitPrices.put(TicketType.TEEN, teen);
        unitPrices.put(TicketType.CHILDREN, children);

        // discount for Seniors...
        double senior = (1 - (double) seniorDiscountPercentage / 100D) * adult;
        unitPrices.put(TicketType.SENIOR, senior);
    }

    private void validate(double value, String name) {
        if (value <= 0D) {
            throw new IllegalStateException("expected " + name + " unit price to be > 0");
        }
    }

    public double price(TicketType type) {
        return unitPrices.getOrDefault(type, 0D);
    }
}
