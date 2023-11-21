package christmas;

import christmas.view.InputValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class DayInputTest {
    private final InputValidator validator = new InputValidator();

    @DisplayName("문자열 입력 시 예외 발생")
    @ParameterizedTest
    @ValueSource(strings = {"a", "가나다라", "test", "3a"})
    void inputDayIsNotNumber(String s) {
        assertThatThrownBy(() -> Integer.parseInt(s))
                .isInstanceOf(NumberFormatException.class);
    }

    @DisplayName("31보다 큰 숫자 입력 시 예외 발생")
    @ParameterizedTest
    @ValueSource(ints = {32, 45, 68, 90, Integer.MAX_VALUE})
    void inputDayUpperThanThirtyOne(int number) {
        assertThatThrownBy(() -> validator.validateDay(number))
                .isInstanceOf(IllegalArgumentException.class);
    }
    @DisplayName("1보다 작은 숫자 입력 시 예외 발생")
    @ParameterizedTest
    @ValueSource(ints = {0, -1, -5, -100, Integer.MIN_VALUE})
    void inputDayLowerThanOne(int number) {
        assertThatThrownBy(() -> validator.validateDay(number))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
