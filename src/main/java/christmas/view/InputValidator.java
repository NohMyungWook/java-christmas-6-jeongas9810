package christmas.view;

public class InputValidator {
    public void validateDay(int day) {
        if (day < 1 || day > 31) {
            throw new IllegalArgumentException();
        }
    }
}
