package christmas.model;

public class Menu {
    private final MenuType menuType;
    private final String koName;
    private final int price;

    public Menu(MenuType menuType, String koName, int price) {
        this.menuType = menuType;
        this.koName = koName;
        this.price = price;
    }

    public MenuType getMenuType() {
        return menuType;
    }

    public String getKoName() {
        return koName;
    }
}
