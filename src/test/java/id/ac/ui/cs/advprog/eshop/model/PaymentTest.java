package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class PaymentTest {
    @Test
    void testCreatePayment() {
        List<Product> products = new ArrayList<>();
        Product product = new Product();
        product.setProductId("test-product");
        product.setProductName("Test Product");
        product.setProductQuantity(1);
        products.add(product);

        Order order = new Order("test-id", products, 123L, "test");

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