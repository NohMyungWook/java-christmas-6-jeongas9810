package christmas.common;

import christmas.model.Benefit;
import christmas.model.Order;
import christmas.model.Receipt;
import christmas.model.constant.EventType;
import christmas.model.constant.MenuItems;
import christmas.model.constant.MenuType;
import christmas.model.constant.SpecialDay;
import christmas.view.constant.Badge;

import java.time.LocalDate;
import java.util.List;

public class Calculator {
    private final static int INIT_INTEGER = 0;
    private final static int CHANGE_SIGN = -1;
    private final static int CAN_PARTICIPATE_AMOUNT = 10000;
    private final static int GET_GIFT_AMOUNT = 120000;
    private final static int XMAS_DISCOUNT = 1000;
    private final static int SPECIAL_DISCOUNT = 1000;
    private final static int MENU_DISCOUNT = 2023;
    private final static int FRI = 5;
    private final static int SAT = 6;
    private final static int FINISH_XMAS_DISCOUNT_DAY = 25;

    public int getTotalOrderAmount(List<Receipt> receipts) {
        return receipts.stream().mapToInt(Receipt::getAmount).sum();
    }

    public int getTotalDiscountAmount(List<Benefit> benefits) {
        return benefits.stream().mapToInt(Benefit::getPrice).sum();
    }

    public Benefit canParticipateEvent(int amount) {
        if (amount < CAN_PARTICIPATE_AMOUNT) {
            return new Benefit(EventType.NONE, INIT_INTEGER);
        }
        return new Benefit(EventType.PARTICIPATE, INIT_INTEGER);
    }

    public Benefit canGetGift(int amount) {
        if (amount >= GET_GIFT_AMOUNT) {
            return new Benefit(EventType.GIFT, MenuItems.CHAMPAGNE.getPrice() * CHANGE_SIGN);
        }
        return new Benefit(EventType.GIFT, INIT_INTEGER);
    }

    public Benefit getWeekNumber(Order order) {
        int dayOfWeekNumber = order.date().getDayOfWeek().getValue();

        if(dayOfWeekNumber == FRI || dayOfWeekNumber == SAT) {
            return new Benefit(EventType.WEEKEND, order.getMenuTypeCnt(MenuType.MAIN) * MENU_DISCOUNT * CHANGE_SIGN);
        }
        return new Benefit(EventType.WEEKDAY, order.getMenuTypeCnt(MenuType.DESSERT) * MENU_DISCOUNT * CHANGE_SIGN);
    }

    public Benefit getXMasDiscount(int dayOfMonth) {
        if (dayOfMonth > FINISH_XMAS_DISCOUNT_DAY) {
            return new Benefit(EventType.CHRISTMAS, INIT_INTEGER);
        }
        return new Benefit(EventType.CHRISTMAS, ((dayOfMonth-1) * 100 + XMAS_DISCOUNT) * CHANGE_SIGN);
    }

    public Benefit getSpecialDiscount(LocalDate date) {
        SpecialDay[] specialDays = SpecialDay.values();

        for (SpecialDay specialDay : specialDays) {
            if (specialDay.getDay() == date.getDayOfMonth()) {
                return new Benefit(EventType.SPECIAL, SPECIAL_DISCOUNT * CHANGE_SIGN);
            }
        }
        return new Benefit(EventType.SPECIAL, INIT_INTEGER);
    }

    public Badge getBadgeWithBenefitAmount(int amount) {
        if (amount >= Badge.SANTA.getPrice()) return Badge.SANTA;
        if (amount >= Badge.TREE.getPrice()) return Badge.TREE;
        if (amount >= Badge.STAR.getPrice()) return Badge.STAR;
        return Badge.NONE;
    }
}
