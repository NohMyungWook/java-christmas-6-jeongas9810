package christmas.model;

import christmas.model.constant.MenuType;

import java.time.LocalDate;
import java.util.List;

public record Order(LocalDate date, List<Receipt> receipts) {

    public int getMenuTypeCnt(MenuType menuType) {
        int cnt = 0;
        for (Receipt receipt : receipts) {
            if (receipt.getMenu().menuType() == menuType) {
                cnt+= receipt.getCount();
            }
        }
        return cnt;
    }
}
