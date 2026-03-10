package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PaymentServiceImplTest {

    @InjectMocks
    PaymentServiceImpl paymentService;

    @Mock
    PaymentRepository paymentRepository;

    @Mock
    OrderService orderService;

    private Order order;
    private List<Product> products;

    @BeforeEach
    void setUp() {
        products = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(2);
        products.add(product1);

        order = new Order("13652556-012a-4c07-b546-54eb1396d79b",
                products, 17085600001L, "Safira Sudrajat");
    }

    @Test
    void testAddPaymentVoucherSuccess() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");

        when(paymentRepository.save(any(Payment.class))).thenAnswer(i -> i.getArgument(0));

        Payment result = paymentService.addPayment(order, "VOUCHER", paymentData);

        assertEquals("SUCCESS", result.getStatus());
        verify(paymentRepository, times(1)).save(any(Payment.class));
    }

    @Test
    void testAddPaymentVoucherInvalidLength() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP123");

        when(paymentRepository.save(any(Payment.class))).thenAnswer(i -> i.getArgument(0));

        Payment result = paymentService.addPayment(order, "VOUCHER", paymentData);

        assertEquals("REJECTED", result.getStatus());
    }

    @Test
    void testAddPaymentBankTransferSuccess() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("bankName", "BCA");
        paymentData.put("referenceCode", "REF123456");
        
        when(paymentRepository.save(any(Payment.class))).thenAnswer(i -> i.getArgument(0));

        Payment result = paymentService.addPayment(order, "BANK_TRANSFER", paymentData);

        assertEquals("SUCCESS", result.getStatus());
    }

    @Test
    void testAddPaymentBankTransferMissingBankName() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("referenceCode", "REF123456");

        when(paymentRepository.save(any(Payment.class))).thenAnswer(i -> i.getArgument(0));

        Payment result = paymentService.addPayment(order, "BANK_TRANSFER", paymentData);

        assertEquals("REJECTED", result.getStatus());
    }

    @Test
    void testAddPaymentBankTransferMissingReferenceCode() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("bankName", "BCA");

        when(paymentRepository.save(any(Payment.class))).thenAnswer(i -> i.getArgument(0));

        Payment result = paymentService.addPayment(order, "BANK_TRANSFER", paymentData);

        assertEquals("REJECTED", result.getStatus());
    }

    @Test
    void testSetStatusToSuccess() {
        Map<String, String> paymentData = new HashMap<>();
        Payment payment = new Payment("test-id", "BANK_TRANSFER", paymentData, order);

        when(paymentRepository.save(any(Payment.class))).thenReturn(payment);

        Payment result = paymentService.setStatus(payment, "SUCCESS");

        assertEquals("SUCCESS", result.getStatus());
        verify(orderService, times(1)).updateStatus(order.getId(), "SUCCESS");
    }

    @Test
    void testSetStatusToRejected() {
        Map<String, String> paymentData = new HashMap<>();
        Payment payment = new Payment("test-id", "BANK_TRANSFER", paymentData, order);

        when(paymentRepository.save(any(Payment.class))).thenReturn(payment);

        Payment result = paymentService.setStatus(payment, "REJECTED");

        assertEquals("REJECTED", result.getStatus());
        verify(orderService, times(1)).updateStatus(order.getId(), "FAILED");
    }

    @Test
    void testGetPayment() {
        String paymentId = "test-id";
        Map<String, String> paymentData = new HashMap<>();
        Payment payment = new Payment(paymentId, "BANK_TRANSFER", paymentData, order);

        when(paymentRepository.findById(paymentId)).thenReturn(payment);

        Payment result = paymentService.getPayment(paymentId);

        assertEquals(paymentId, result.getId());
    }

    @Test
    void testGetAllPayments() {
        List<Payment> payments = new ArrayList<>();
        Map<String, String> paymentData = new HashMap<>();
        payments.add(new Payment("1", "VOUCHER", paymentData, order));
        payments.add(new Payment("2", "BANK_TRANSFER", paymentData, order));

        when(paymentRepository.findAll()).thenReturn(payments);

        List<Payment> results = paymentService.getAllPayments();

        assertEquals(2, results.size());
    }
}