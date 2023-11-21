package christmas.model;

import christmas.model.constant.EventType;

import java.text.DecimalFormat;

public class Benefit {
    private final EventType event;
    private final int discountAmount;

    public Benefit(EventType event, int discountAmount) {
        this.event = event;
        this.discountAmount = discountAmount;
    }

    public EventType getEventType() {
        return event;
    }

    public int getDiscountAmount() {
        return discountAmount;
    }
}
