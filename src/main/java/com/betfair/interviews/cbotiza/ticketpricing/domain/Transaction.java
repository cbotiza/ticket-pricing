package com.betfair.interviews.cbotiza.ticketpricing.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.stream.IntStream;

@Data
@AllArgsConstructor
public class Transaction {
    private long transactionId;

    private List<Integer> ages;

    public IntStream agesAsStream() {
        return ages.stream().mapToInt(Integer::intValue);
    }
}
