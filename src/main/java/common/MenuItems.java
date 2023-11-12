package common;

import christmas.model.MenuType;

// DB없이 메뉴 등록을 위한 enum data
public enum MenuItems {
    PINE_MUSHROOM_SOUP(MenuType.APPETIZER, "양송이수프", 6000),
    TAPAS(MenuType.APPETIZER, "타파스", 5500),
    CAESAR_SALAD(MenuType.APPETIZER, "시저샐러드", 8000),
    T_BONE_STEAK(MenuType.MAIN, "티본스테이크", 55000),
    BARBECUE_RIPS(MenuType.MAIN, "바비큐립", 54000),
    SEAFOOD_PASTA(MenuType.MAIN, "해산물파스타", 35000),
    CHRISTMAS_PASTA(MenuType.MAIN, "크리스마스파스타", 25000),
    CHOCOLATE_CAKE(MenuType.DESSERT, "초코케이크", 15000),
    ICE_CREAM(MenuType.DESSERT, "아이스크림", 5000),
    ZERO_COLA(MenuType.DRINK, "제로콜라", 3000),
    RED_WINE(MenuType.DRINK, "레드와인", 60000),
    CHAMPAGNE(MenuType.DRINK, "샴페인", 25000)
    ;

    private final MenuType menuType;
    private final String koName;
    private final Integer price;

    MenuItems(MenuType menuType, String koName, int price) {
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

    public int getPrice() {
        return price;
    }
}
