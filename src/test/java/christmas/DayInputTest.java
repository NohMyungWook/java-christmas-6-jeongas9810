package christmas;

import christmas.view.InputValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class DayInputTest {
    private final InputValidator validator = new InputValidator();

    @DisplayName("문자열 입력 시 예외 발생")
    @Test
    void inputDayIsNotNumber() {
        assertThatThrownBy(() -> Integer.parseInt("test"))
                .isInstanceOf(NumberFormatException.class);
    }

    @DisplayName("31보다 큰 숫자 입력 시 예외 발생")
    @Test
    void inputDayUpperThanThirtyOne() {
        assertThatThrownBy(() -> validator.validateDay(32))
                .isInstanceOf(IllegalArgumentException.class);
    }
    @DisplayName("1보다 작은 숫자 입력 시 예외 발생")
    @Test
    void inputDayLowerThanOne() {
        assertThatThrownBy(() -> validator.validateDay(0))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
