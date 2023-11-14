package christmas;

import christmas.view.InputValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class OrderInputTest {
    private final InputValidator validator = new InputValidator();

    @DisplayName("공백 포함 시 예외 발생")
    @Test
    void inputOrderContainsBlank() {
        assertThatThrownBy(() -> validator.validateMenuOrder("티본스테이크 - 3, 제로콜라 - 4".split(",")))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("[한글-숫자]의 형식이 아닐 경우 예외 발생")
    @Test
    void inputOrderCheckFormat() {
        assertThatThrownBy(() -> validator.validateMenuOrder("티본스테이크-a,cola-3".split(",")))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("주문 개수가 1보다 작을 경우 예외 발생")
    @Test
    void inputOrderCountIsLowerThanOne() {
        assertThatThrownBy(() -> validator.validateMenuOrder("티본스테이크-3,제로콜라-0".split(",")))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("메뉴판에 없는 메뉴 포함 시 예외 발생")
    @Test
    void inputOrderInvalidMenuName() {
        assertThatThrownBy(() -> validator.validateReceipt(validator.validateMenuOrder("엄청맛있는밥-3,제로콜라-4".split(","))))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("메뉴의 수량이 20개가 넘을 경우 예외 발생")
    @Test
    void inputOrderCountIsUpperThanTwenty() {
        assertThatThrownBy(() -> validator.validateReceipt(validator.validateMenuOrder("티본스테이크-17,제로콜라-4".split(","))))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
