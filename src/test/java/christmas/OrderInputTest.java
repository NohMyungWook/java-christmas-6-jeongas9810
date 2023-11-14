package christmas;

import christmas.view.InputValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class OrderInputTest {
    private static final String COMMA = ",";
    private final InputValidator validator = new InputValidator();

    @DisplayName("공백 포함 시 예외 발생")
    @ParameterizedTest
    @ValueSource(strings = {" ", "티본스테이크- 3", "제로콜라 - 1", "초코케이크 - 3,제로콜라-1"})
    void inputOrderContainsBlank(String blank) {
        assertThatThrownBy(() -> validator.validateMenuOrder(blank.split(COMMA)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("[한글-숫자]의 형식이 아닐 경우 예외 발생")
    @ParameterizedTest
    @ValueSource(strings = {"tBoneSteak-3", "cola-a", "콜라-four", "티본스테이크-3,cola-1"})
    void inputOrderCheckFormat(String formatCheck) {
        assertThatThrownBy(() -> validator.validateMenuOrder(formatCheck.split(COMMA)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("주문 개수가 1보다 작을 경우 예외 발생")
    @ParameterizedTest
    @ValueSource(strings = {"티본스테이크-0,제로콜라-4", "초코케이크-"+Integer.MIN_VALUE})
    void inputOrderCountIsLowerThanOne(String underZero) {
        assertThatThrownBy(() -> validator.validateMenuOrder(underZero.split(COMMA)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("메뉴판에 없는 메뉴 포함 시 예외 발생")
    @ParameterizedTest
    @ValueSource(strings = {"공기밥-1,제로콜라2", "코카콜라-5", "티본스테이크-1,머시룸스프-4"})
    void inputOrderInvalidMenuName(String invalidMenu) {
        assertThatThrownBy(() -> validator.validateReceipt(validator.validateMenuOrder(invalidMenu.split(COMMA))))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("메뉴의 수량이 20개가 넘을 경우 예외 발생")
    @ParameterizedTest
    @ValueSource(strings = {"제로콜라-24", "티본스테이크-10,초코케이크-11", "샴페인-4,티본스테이크-6,초코케이크-8,제로콜라-7"})
    void inputOrderCountIsUpperThanTwenty(String manyCount) {
        assertThatThrownBy(() -> validator.validateReceipt(validator.validateMenuOrder(manyCount.split(COMMA))))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("음료만 주문할 경우 예외 발생")
    @ParameterizedTest
    @ValueSource(strings = {"제로콜라-1", "제로콜라-2,레드와인-3", "샴페인-8,제로콜라-1,레드와인-2"})
    void inputOrderIsNotOnlyDrink() {
        assertThatThrownBy(() -> validator.validateReceipt(validator.validateMenuOrder("샴페인-1,레드와인-2,제로콜라-4".split(COMMA))))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
