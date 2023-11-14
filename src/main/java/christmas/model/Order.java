package christmas.model;

import java.time.LocalDate;
import java.util.List;

public class Order {
    private LocalDate date;
    private List<Receipt> receipts;

    public Order(LocalDate date, List<Receipt> receipts) {
        this.date = date;
        this.receipts = receipts;
    }
}
