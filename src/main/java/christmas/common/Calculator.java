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
    private final static Integer INIT_INTEGER = 0;
    private final static Integer CHANGE_SIGN = -1;
    private final static Integer CAN_PARTICIPATE_AMOUNT = 100000;
    private final static Integer GET_GIFT_AMOUNT = 120000;
    private final static Integer XMAS_DISCOUNT = 1000;
    private final static Integer SPECIAL_DISCOUNT = 1000;
    private final static Integer MENU_DISCOUNT = 2023;
    private final static Integer FRI = 5;
    private final static Integer SAT = 6;
    private final static Integer FINISH_XMAS_DISCOUNT_DAY = 25;
    private final static Integer STAR_AMOUNT = 5000;
    private final static Integer TREE_AMOUNT = 10000;
    private final static Integer SANTA_AMOUNT = 20000;


    public Integer getTotalOrderAmount(List<Receipt> receipts) {
        int amount = INIT_INTEGER;

        for (Receipt receipt : receipts) {
            amount += (receipt.getCount() * receipt.getMenu().getPrice());
        }

        return amount;
    }

    public Integer getTotalDiscountAmount(List<Benefit> benefits) {
        int amount = INIT_INTEGER;

        for (Benefit benefit : benefits) {
            amount += benefit.getPrice();
        }

        return amount;
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

    public Benefit getWeekNumber(Order order, int dayOfWeekNumber) {
        if(dayOfWeekNumber == FRI || dayOfWeekNumber == SAT) {
            return new Benefit(EventType.WEEKEND, order.getMenuTypeCnt(MenuType.MAIN) * MENU_DISCOUNT * CHANGE_SIGN);
        }
        return new Benefit(EventType.WEEKDAY, order.getMenuTypeCnt(MenuType.DESSERT) * MENU_DISCOUNT * CHANGE_SIGN);
    }

    public Benefit getXMasDiscount(int dayOfMonth) {
        if (dayOfMonth > FINISH_XMAS_DISCOUNT_DAY) {
            return new Benefit(EventType.CHRISTMAS, INIT_INTEGER);
        }
        return new Benefit(EventType.CHRISTMAS, (dayOfMonth * 100 + XMAS_DISCOUNT) * CHANGE_SIGN);
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
        if (amount >= SANTA_AMOUNT) return Badge.SANTA;
        if (amount >= TREE_AMOUNT) return Badge.TREE;
        if (amount >= STAR_AMOUNT) return Badge.STAR;
        return Badge.NONE;
    }
}
