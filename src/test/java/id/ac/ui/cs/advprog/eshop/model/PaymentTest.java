package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class PaymentTest {
    @Test
    void testCreatePayment() {
        Order order = new Order("test-id", new ArrayList<>(), 123L, "test");
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("test", "data");

        Payment payment = new Payment("pay-123", "VOUCHER", paymentData, order);

        assertEquals("pay-123", payment.getId());
        assertEquals("VOUCHER", payment.getMethod());
        assertEquals(paymentData, payment.getPaymentData());
        assertEquals(order, payment.getOrder());
        assertEquals("WAITING_PAYMENT", payment.getStatus());
    }
}
