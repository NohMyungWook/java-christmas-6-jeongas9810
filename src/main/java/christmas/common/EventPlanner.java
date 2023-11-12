package christmas.common;

import christmas.controller.MenuController;
import christmas.model.Menu;
import java.util.List;

public class EventPlanner implements Planner{
    private MenuController menuController = new MenuController();

    @Override
    public void start() {
        List<Menu> menuBoard = menuController.setMenuBoard();
    }
}
