package christmas.model;

import java.text.DecimalFormat;

public class Benefit {
    private final EventType event;
    private final int price;

    public Benefit(EventType event, int price) {
        this.event = event;
        this.price = price;
    }

    @Override
    public String toString() {
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        return event.toString() + ": " + decimalFormat.format(price) + "원";
    }
}
