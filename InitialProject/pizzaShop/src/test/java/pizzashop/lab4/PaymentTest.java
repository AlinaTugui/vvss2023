package pizzashop.lab4;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class PaymentTest {
    @Test
    public void testToString() {
        Payment payment = new Payment(1, PaymentType.CASH, 10.0);
        assertEquals("1,CASH,10.0",payment.toString());
    }

    @Test
    public void testGetAmount() {
        Payment payment = new Payment(2, PaymentType.CARD, 15.0);
        assertEquals(15.0, payment.getAmount());
    }
}