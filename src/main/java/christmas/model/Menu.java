package christmas.model;

public class Menu {
    private MenuType menuType;
    private String koName;
    private int price;

    public Menu(MenuType menuType, String koName, int price) {
        this.menuType = menuType;
        this.koName = koName;
        this.price = price;
    }
}
