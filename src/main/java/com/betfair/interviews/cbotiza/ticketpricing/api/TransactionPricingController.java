package com.betfair.interviews.cbotiza.ticketpricing.api;

import com.betfair.interviews.cbotiza.ticketpricing.api.model.TransactionPricingRequest;
import com.betfair.interviews.cbotiza.ticketpricing.api.model.TransactionPricingResponse;
import com.betfair.interviews.cbotiza.ticketpricing.domain.Transaction;
import com.betfair.interviews.cbotiza.ticketpricing.domain.TransactionTotals;
import com.betfair.interviews.cbotiza.ticketpricing.service.DomainMapper;
import com.betfair.interviews.cbotiza.ticketpricing.service.TicketPricingService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequestMapping("/")
public class TransactionPricingController {

    @Autowired
    private TicketPricingService pricingService;

    @PostMapping
    @Operation(summary = "Computes a transaction's total cost, with all discounts applied")
    public ResponseEntity<TransactionPricingResponse> priceOf(@RequestBody @Valid TransactionPricingRequest transactionPricingRequest) {
        Transaction transaction = DomainMapper.fromRequest(transactionPricingRequest);

        TransactionTotals totals = pricingService.totalsFor(transaction);

        return ResponseEntity.ok(DomainMapper.toResponse(totals));
    }
}
