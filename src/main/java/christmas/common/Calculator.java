package christmas.common;

import christmas.model.Benefit;
import christmas.model.Receipt;

import java.util.List;

public class Calculator {
    private final static Integer INIT_INTEGER = 0;
    public int getTotalAmount(List<Receipt> receipts, List<Benefit> benefits) {
        int amount = INIT_INTEGER;

        for (Receipt receipt : receipts) {
            amount += (receipt.getCount() * receipt.getMenu().getPrice());
        }

        return amount;
    }

}
