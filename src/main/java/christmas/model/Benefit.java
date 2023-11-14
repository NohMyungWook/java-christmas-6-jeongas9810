package christmas.model;

import christmas.model.constant.EventType;

public class Benefit {
    private final EventType event;

    public Benefit(EventType event) {
        this.event = event;
    }

    @Override
    public String toString() {
        return event.toString() + ": ";
    }
}
