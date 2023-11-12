package christmas;

import christmas.controller.EventPlanner;
import christmas.controller.Planner;

public class Application {
    public static void main(String[] args) {
        Planner eventPlanner = new EventPlanner();
        eventPlanner.start();
    }
}
