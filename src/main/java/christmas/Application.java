package christmas;

import camp.nextstep.edu.missionutils.Console;
import christmas.common.EventPlanner;

public class Application {
    public static void main(String[] args) {
        EventPlanner eventPlanner = new EventPlanner();
        eventPlanner.start();
        Console.close();
    }
}