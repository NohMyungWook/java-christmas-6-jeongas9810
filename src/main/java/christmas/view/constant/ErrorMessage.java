package christmas.view.constant;

public enum ErrorMessage {
    PREFIX("[ERROR] %s "),
    SUFFIX("다시 입력해 주세요."),
    INPUT_DATE("유효하지 않은 날짜입니다."),
    INPUT_MENU("유효하지 않은 주문입니다."),
    CANNOT_ORDER_ONLY_DRINK("음료만 주문 시, 주문할 수 없습니다."),
    MAX_MENU_COUNT("메뉴는 한 번에 최대 20개까지만 주문할 수 있습니다.\n" +
            "(e.g. 시저샐러드-1, 티본스테이크-1, 크리스마스파스타-1, 제로콜라-3, 아이스크림-1의 총개수는 7개)")
    ;

    private final String errMessage;

    ErrorMessage(String errMessage) {
        this.errMessage = errMessage;
    }

    public void print() {
        System.out.println(String.format(PREFIX.errMessage, errMessage) + SUFFIX.errMessage);
    }
}
