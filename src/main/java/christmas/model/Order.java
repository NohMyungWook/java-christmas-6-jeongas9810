package christmas.model;

import java.time.LocalDate;
import java.util.List;

public class Order {
    private final LocalDate date;
    private final List<Receipt> receipts;

    public Order(LocalDate date, List<Receipt> receipts) {
        this.date = date;
        this.receipts = receipts;
    }

    public List<Receipt> getReceipts() {
        return receipts;
    }

    public LocalDate date() {
        return date;
    }
}
