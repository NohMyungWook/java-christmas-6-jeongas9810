package christmas.common;

import christmas.model.Benefit;
import christmas.model.Order;

import java.util.List;

public interface Planner {
    void start();

    Order reservation();

    void printOrderMenuAndAmount(Order order, int orderAmount);

    List<Benefit> makeBenefits(Order order, int orderAmount);

    List<Benefit> setBenefits(Order order, List<Benefit> benefits);

    void printEvent(List<Benefit> benefits, int orderAmount);

    void printEvent(int amount);
}
