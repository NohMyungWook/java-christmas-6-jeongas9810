package christmas.model;

import christmas.model.constant.MenuItems;

public class Receipt {
    private static final int MENU_MIN_COUNT = 1;
    private final Menu menu;
    private final int count;

    // gift menu 등록을 위한 생성자. 추후 증정 메뉴 변경 시 MenuItems 뒤 상수만 바꾸면 됨.
    public Receipt() {
        this.menu = MenuItems.getMenuByName(MenuItems.CHAMPAGNE.getKoName());
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
        return menu.koName() + " " + count + "개";
    }
}
