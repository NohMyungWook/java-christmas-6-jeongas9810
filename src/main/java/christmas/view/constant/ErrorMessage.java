package christmas.view.constant;

public enum ErrorMessage {
    PREFIX("[ERROR] %s "),
    SUFFIX("다시 입력해 주세요."),

    ERR_INPUT_DATE("유효하지 않은 날짜입니다."),
    ERR_INPUT_MENU("유효하지 않은 주문입니다."),
    ERR_INPUT_NUMBER("숫자만 입력해 주세요.")
    ;

    private final String errMessage;

    ErrorMessage(String errMessage) {
        this.errMessage = errMessage;
    }

    public void print() {
        System.out.println(String.format(PREFIX.errMessage, errMessage) + SUFFIX.errMessage);
    }
}
