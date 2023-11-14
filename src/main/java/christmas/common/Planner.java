package christmas.common;

import christmas.model.Order;

public interface Planner {
    void start();
    Order reservation();
}
