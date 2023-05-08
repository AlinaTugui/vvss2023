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

@ExtendWith(MockitoExtension.class)
public class PizzaServiceStep3Test {
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

        Payment payment1 = new Payment(1, PaymentType.CASH, 10.0);
        Payment payment2 = new Payment(2, PaymentType.CARD, 15.0);
        pizzaService.addPayment(payment1.getTableNumber(), payment1.getType(), payment1.getAmount());
        pizzaService.addPayment(payment2.getTableNumber(), payment2.getType(), payment2.getAmount());
    }

    @Test
    public void getPaymentsTest() {
        assertEquals(2, pizzaService.getPayments().size());
    }

    @Test
    public void getTotalAmountTest() {
        assertEquals(10.0, pizzaService.getTotalAmount(PaymentType.CASH));
    }
}