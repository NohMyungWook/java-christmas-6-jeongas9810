package christmas.common;

import christmas.model.Benefit;
import christmas.model.Order;

import java.util.List;

public interface Planner {
    void start();
    Order reservation();
    List<Benefit> checkCanParticipate(Order order);
}
