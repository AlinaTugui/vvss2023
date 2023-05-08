package pizzashop.lab4;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;
import pizzashop.repository.MenuRepository;
import pizzashop.repository.PaymentRepository;
import pizzashop.service.PizzaService;

import java.io.PrintWriter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
public class PizzaServiceStep2Test {
    static PizzaService pizzaService;
    static MenuRepository menuRepository;
    static PaymentRepository paymentRepository;

    @BeforeAll
    public static void setUp() throws Exception {
        PrintWriter writer = new PrintWriter("data/payments.txt");
        writer.print("");
        writer.close();
        menuRepository = new MenuRepository();
        paymentRepository = new PaymentRepository();
        pizzaService = new PizzaService(menuRepository, paymentRepository);

        Payment payment1 = mock(Payment.class);
        Payment payment2 = mock(Payment.class);

        pizzaService.addPayment(payment1.getTableNumber(), PaymentType.CASH, payment1.getAmount());
        pizzaService.addPayment(payment2.getTableNumber(), PaymentType.CASH, payment2.getAmount());

    }


    @Test
    public void getPaymentsTest() {
        assertEquals(2, pizzaService.getPayments().size());
    }

    @Test
    public void getTotalAmountTest() {
        assertEquals(0, pizzaService.getTotalAmount(PaymentType.CASH));
    }
}