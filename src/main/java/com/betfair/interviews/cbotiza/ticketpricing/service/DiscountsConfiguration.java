package com.betfair.interviews.cbotiza.ticketpricing.service;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DiscountsConfiguration {

    @Bean
    @ConfigurationProperties(value = "discounts.children")
    public ChildrenGroupDiscount childrenGroupDiscount() {
        return new ChildrenGroupDiscount();
    }
}
