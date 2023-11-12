package christmas.controller;

import christmas.model.Menu;
import common.MenuItems;

import java.util.ArrayList;
import java.util.List;

public class MenuController {
    public List<Menu> setMenuBoard() {
        List<Menu> menuBoard = new ArrayList<>();
        MenuItems[] menuItems = MenuItems.values();
        for (MenuItems menuItem : menuItems) {
            menuBoard.add(new Menu(menuItem.getMenuType(), menuItem.getKoName(), menuItem.getPrice()));
        }
        return menuBoard;
    }
}
