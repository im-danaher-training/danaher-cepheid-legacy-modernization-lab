package com.cepheid.training;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LegacyOrderServiceTest {
    @Test
    void statusIsReady() {
        LegacyOrderService service = new LegacyOrderService();
        assertEquals("READY", service.getStatus());
    }
}
