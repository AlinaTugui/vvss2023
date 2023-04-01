package pizzashop.service;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import pizzashop.model.PaymentType;
import pizzashop.repository.MenuRepository;
import pizzashop.repository.PaymentRepository;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.fail;

class PizzaServiceTest {
    private PaymentRepository payRepo;
    private MenuRepository menuRepo;
    private PizzaService service;


    @BeforeEach
    void setUp() {
        payRepo = new PaymentRepository();
        menuRepo = new MenuRepository();
        service = new PizzaService(menuRepo, payRepo);

    }

    @AfterEach
    void tearDown() {
        payRepo.deleteFileContent();
    }

    @Test
    @Tag("BVA")
    @DisplayName("Test for invalid table Bva")
    void addPayment_1_bva() throws Exception {
        try {
            service.addPayment(9, PaymentType.Card, 200.00);
        } catch (Exception e) {
            assert e.getMessage().equals("Nr mesei trebuie sa fie cuprins intre 1 si 8, tipul platii trebuie sa fie CASH/CARD, valoarea trebuie sa fie pozitiva");
        }

    }

    @Test
    @Tag("BVA")
    @DisplayName("Test for invalid table Bva")
    void addPayment_2_bva() throws Exception {
        try {
            service.addPayment(0, PaymentType.Card, 34.00);
        } catch (Exception e) {
            assert e.getMessage().equals("Nr mesei trebuie sa fie cuprins intre 1 si 8, tipul platii trebuie sa fie CASH/CARD, valoarea trebuie sa fie pozitiva");
        }

    }

    @Test
    @Tag("BVA")
    @DisplayName("Test for valid table Bva")
    void addPayment_3_bva() throws Exception {
        try {
            service.addPayment(8, PaymentType.Card, 200.00);
            assert 1 == service.getPayments().size();
        } catch (Exception e) {
            fail(e.getMessage());
        }

    }

    @Test
    @Tag("BVA")
    @DisplayName("Test for invalid amount Bva")
    void addPayment_4_bva() throws Exception {
        try {
            service.addPayment(2, PaymentType.Card, -200);

        } catch (Exception e) {
            assert e.getMessage().equals("Nr mesei trebuie sa fie cuprins intre 1 si 8, tipul platii trebuie sa fie CASH/CARD, valoarea trebuie sa fie pozitiva");
        }
    }

    @Test
    @RepeatedTest(1)
    @Tag("ECP")
    @DisplayName("Test for invalid table ecp")
    void addPayment_5_ecp() throws Exception {
        try {
            service.addPayment(10, PaymentType.Cash, 200);

        } catch (Exception e) {
            assert e.getMessage().equals("Nr mesei trebuie sa fie cuprins intre 1 si 8, tipul platii trebuie sa fie CASH/CARD, valoarea trebuie sa fie pozitiva");
        }
    }

    @Test
    @Tag("ECP")
    @DisplayName("Test for valid table ecp")
    void addPayment_6_ecp() throws Exception {
        try {
            service.addPayment(3, PaymentType.Card, 200);
            assert 1 == service.getPayments().size();

        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    @Tag("ECP")
    @DisplayName("Test for invalid table ecp")
    @Timeout(value = 100, unit = TimeUnit.MILLISECONDS)
    void addPayment_7_ecp() throws Exception {
        try {
            service.addPayment(-4, PaymentType.Card, -200);
        } catch (Exception e) {
            assert e.getMessage().equals("Nr mesei trebuie sa fie cuprins intre 1 si 8, tipul platii trebuie sa fie CASH/CARD, valoarea trebuie sa fie pozitiva");
        }
    }


    @ParameterizedTest
    @ValueSource(ints = {7})
    @DisplayName("Test for valid table bva")
    void add_payment_8_ecp(Integer table) {
        try {
            service.addPayment(table, PaymentType.Card, 200);
            assert 1 == service.getPayments().size();
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @ParameterizedTest
    @ValueSource(ints = {77})
    @Disabled
    void addPayment_disabled_9_ecp(Integer table) throws Exception {
        try {
            service.addPayment(table, PaymentType.Card, 200);
        } catch (Exception e) {
            assert e.getMessage().equals("Nr mesei trebuie sa fie cuprins intre 1 si 8, tipul platii trebuie sa fie CASH/CARD, valoarea trebuie sa fie pozitiva");
        }
    }

}