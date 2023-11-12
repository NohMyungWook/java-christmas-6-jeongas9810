package christmas;

import christmas.common.EventPlanner;
import christmas.common.Planner;

public class Application {
    public static void main(String[] args) {
        Planner eventPlanner = new EventPlanner();
        eventPlanner.start();
    }
}
