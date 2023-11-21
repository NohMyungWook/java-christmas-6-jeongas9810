package christmas.view.constant;

public enum ViewMessage {
    // Output View
    WELCOME("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.\n"),
    INTRODUCE("12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n"),
    ORDER("<주문 메뉴>\n"),
    AMOUNT_BEFORE_DISCOUNT("<할인 전 총주문 금액>\n"),
    GIFT_MENU("<증정 메뉴>\n"),
    BENEFITS("<혜택 내역>\n"),
    BENFIT_AMOUNT("<총혜택 금액>\n"),
    AMOUNT_AFTER_DISCOUNT("<할인 후 예상 결제 금액>\n"),
    BADGE("<12월 이벤트 배지>\n"),
    BENEFIT("%s: %,d원"),
    RECEIPT("%s %d개"),
    AMOUNT("%,d원\n"),
    NONE("없음\n"),

    // Input View
    ASK_DATE("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)\n"),
    ASK_ORDER("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)\n"),

    // Common
    PRINT_MESSAGE("%s\n"),
    PRINT_LINE(System.lineSeparator())
    ;

    private final String message;

    ViewMessage(String message) {
        this.message = message;
    }

    public void print() {
        System.out.print(message);
    }

    public void renderAndPrint(String s) {
        System.out.printf(message, s);
    }

    public void renderAndPrint(int d) {
        System.out.printf(message, d);
    }

    public void renderAndPrint(String s, int d) {
        System.out.printf(message, s, d);
    }
}
