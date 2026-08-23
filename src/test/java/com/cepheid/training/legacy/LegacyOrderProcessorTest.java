package com.cepheid.training.legacy;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LegacyOrderProcessorTest {

    @Test
    void acceptsSmallCreditOrderInUsWest() {
        LegacyOrderProcessor processor = new LegacyOrderProcessor();
        String result = processor.processOrder(
                "Fictional Regional Lab", "US-WEST",
                List.of("CEPH-100"), List.of(25.0), List.of(2), "CREDIT");
        assertEquals("ORDER_ACCEPTED", result);
    }
}
