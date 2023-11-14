package christmas.model;

import common.MenuItems;

public class Receipt {
    private final int MENU_MIN_COUNT = 1;
    private final Menu menu;
    private final int count;

    public Receipt(Menu menu, int count) {
        validateCount(count);
        this.menu = menu;
        this.count = count;
    }

    public Receipt(String koName, int count) {
        validateCount(count);
        this.menu = MenuItems.getMenuByName(koName);
        this.count = count;
    }

    private void validateCount(int count) {
        if (count < MENU_MIN_COUNT) {
            throw new IllegalArgumentException();
        }
    }

    public Menu getMenu() {
        return menu;
    }

    public Integer getCount() {
        return count;
    }

    @Override
    public String toString() {
        return menu.getKoName() + " " + count + "개";
    }
}
