package christmas.model.constant;

public enum SpecialDay {
    THREE(3), TEN(10), SEVENTEEN(17), TWENTY_FOUR(24), TWENTY_FIVE(25), THIRTY_ONE(31);

    private final Integer day;

    SpecialDay(int day) {
        this.day = day;
    }

    public Integer getDay() {
        return day;
    }
}
