package christmas.model;

import christmas.model.constant.MenuType;

import java.time.LocalDate;
import java.util.List;

public class Order {
    private final LocalDate date;
    private final List<Receipt> receipts;

    public Order(LocalDate date, List<Receipt> receipts) {
        this.date = date;
        this.receipts = receipts;
    }

    public int getMenuTypeCnt(MenuType menuType) {
        int cnt = 0;
        for (Receipt receipt : receipts) {
            if (receipt.getMenu().getMenuType() == menuType) {
                cnt ++;
            }
        }
        return cnt;
    }

    public List<Receipt> getReceipts() {
        return receipts;
    }

    public LocalDate getDate() {
        return date;
    }
}
