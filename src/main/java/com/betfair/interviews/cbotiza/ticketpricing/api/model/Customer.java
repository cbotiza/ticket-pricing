package com.betfair.interviews.cbotiza.ticketpricing.api.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record Customer(@NotNull String name, @Min(0) int age) {
}
