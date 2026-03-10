package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class PaymentRepositoryTest {
    private PaymentRepository paymentRepository;
    private Payment payment;
    private Order order;
    private List<Product> products;

    @BeforeEach
    void setUp() {
        paymentRepository = new PaymentRepository();

        products = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(2);
        products.add(product1);

        order = new Order("order-123", products, 123L, "test");

        Map<String, String> paymentData = new HashMap<>();
        payment = new Payment("pay-123", "VOUCHER", paymentData, order);
    }

    @Test
    void testSaveAndFindById() {
        Payment saved = paymentRepository.save(payment);
        Payment found = paymentRepository.findById("pay-123");

        assertEquals(saved, found);
    }

    @Test
    void testSaveUpdate() {
        paymentRepository.save(payment);

        Map<String, String> newData = new HashMap<>();
        newData.put("new", "data");
        Payment updated = new Payment("pay-123", "VOUCHER", newData, order);
        updated.setStatus("SUCCESS");

        paymentRepository.save(updated);
        Payment found = paymentRepository.findById("pay-123");

        assertEquals("SUCCESS", found.getStatus());
        assertEquals(newData, found.getPaymentData());
    }

    @Test
    void testFindAll() {
        paymentRepository.save(payment);

        Map<String, String> paymentData2 = new HashMap<>();
        Payment payment2 = new Payment("pay-456", "BANK_TRANSFER", paymentData2, order);
        paymentRepository.save(payment2);

        List<Payment> all = paymentRepository.findAll();
        assertEquals(2, all.size());
    }
}