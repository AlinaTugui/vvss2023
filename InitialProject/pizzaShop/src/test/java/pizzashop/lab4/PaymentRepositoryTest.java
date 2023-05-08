package pizzashop.lab4;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;
import pizzashop.repository.PaymentRepository;

import java.io.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class PaymentRepositoryTest {
    @InjectMocks
    PaymentRepository paymentRepository=new PaymentRepository();

    @Test
    public void testAddAndGetAll()  {
        Payment payment1 = new Payment(1, PaymentType.CASH, 10.0);
        Payment payment2 = new Payment(2, PaymentType.CARD, 15.0);

        paymentRepository.add(payment1);
        paymentRepository.add(payment2);

        List<Payment> paymentList = paymentRepository.getAll();
        assertEquals(payment1, paymentList.get(paymentList.size()-2));
        assertEquals(payment2, paymentList.get(paymentList.size()-1));
    }

    @Test
    public void testWriteAll() throws IOException {
        Payment payment1 = new Payment(1, PaymentType.CASH, 10.0);
        Payment payment2 = new Payment(2, PaymentType.CARD, 15.0);
        paymentRepository.add(payment1);
        paymentRepository.add(payment2);

        paymentRepository.writeAll();

        File file = new File("data/payments.txt");
        assertTrue(file.exists());
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        String paymentData1= br.readLine();
        String paymentData2 = br.readLine();
        while ((line = br.readLine()) != null) {
            paymentData1 = paymentData2;
            paymentData2 = line;
        }
        br.close();

        assertEquals(paymentData1,payment1.toString());
        assertEquals(paymentData2,payment2.toString());
    }
}