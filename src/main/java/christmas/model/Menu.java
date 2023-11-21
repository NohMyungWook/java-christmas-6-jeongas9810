package christmas.model;

import christmas.model.constant.MenuType;

public record Menu(MenuType menuType, String koName, int price) {
}