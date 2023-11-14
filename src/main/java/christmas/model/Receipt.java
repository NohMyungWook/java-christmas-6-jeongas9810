package christmas.model;

import christmas.model.constant.MenuItems;

public class Receipt {
    private static final int MENU_MIN_COUNT = 1;
    private final Menu menu;
    private final int count;

    public Receipt() {
        this.menu = MenuItems.getMenuByName(MenuItems.NONE.getKoName());
        this.count = MENU_MIN_COUNT;
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
