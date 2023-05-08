package pizzashop.service;

import pizzashop.model.MenuDataModel;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;
import pizzashop.repository.MenuRepository;
import pizzashop.repository.PaymentRepository;

import java.util.List;

public class PizzaService {

    private MenuRepository menuRepo;
    private PaymentRepository payRepo;

    public PizzaService(MenuRepository menuRepo, PaymentRepository payRepo){
        this.menuRepo=menuRepo;
        this.payRepo=payRepo;
    }

    public List<MenuDataModel> getMenuData(){return menuRepo.getMenu();}

    public List<Payment> getPayments(){return payRepo.getAll(); }

    public void addPayment(int table, PaymentType type, double amount) throws Exception {
        Payment payment = new Payment(table, type, amount);
            payRepo.add(payment);

    }
    public double getTotalAmount(PaymentType type) {
        double total = 0.0f;
        List<Payment> list = getPayments();
        if (list == null)
            return total;
        if (list.isEmpty())
            return total;
        for (Payment payment : list) {
            if (payment.getType().equals(type))
                total += payment.getAmount();
        }
        return total;
    }

}
