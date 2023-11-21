package christmas;

import christmas.common.Calculator;
import christmas.model.Benefit;
import christmas.model.Order;
import christmas.model.Receipt;
import christmas.model.constant.EventType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class CalculateTest {
    private static final Calculator calculator = new Calculator();
    private static final List<Receipt> receipts = List.of(new Receipt("티본스테이크", 5), new Receipt("아이스크림", 4));
    private static final List<Benefit> benefits = List.of(
            new Benefit(EventType.WEEKDAY, -2023*5),
            new Benefit(EventType.SPECIAL, -1000),
            new Benefit(EventType.GIFT, -25000),
            new Benefit(EventType.CHRISTMAS, -1400)
    );
    private static final Order weekendOrder = new Order(LocalDate.of(2023, 12, 8), receipts);
    private static final Order weekdayOrder = new Order(LocalDate.of(2023, 12, 11), receipts);

    @DisplayName("Receipt list에 등록된 메뉴 가격들의 총합")
    @Test
    void totalAmountCalc() {
        assertEquals(calculator.getTotalOrderAmount(receipts), 55000 * 5 + 5000 * 4);
    }

    @DisplayName("Benefit list에 등록된 혜택 가격들의 총합")
    @Test
    void totalAmountDiscount() {
        assertEquals(calculator.getTotalDiscountAmount(benefits), -2023 * 5 -1000 -25000 -1400);
    }

    @DisplayName("총 구매금액이 10000원 이하일 경우 event참여 불가")
    @Test
    void canParticipateEvent() {
        // 1000원 구매 시, 이벤트 Benefit 객체의 price가 0인가에 대한 코드
        assertEquals(calculator.canParticipateEvent(1000).getDiscountAmount(), 0);
    }

    @DisplayName("총 구매금액이 120000원 이상일 경우 증정품 지급")
    @Test
    void canGetGift() {
        // 12000원 구매 시, 받는 Benefit의 price가 0이 아닌지에 대한 코드
        assertNotEquals(calculator.canGetGift(120000).getDiscountAmount(), 0);
    }

    @DisplayName("주말일 경우 main메뉴 개수 * 2023원 할인")
    @Test
    void weekendTest() {
        assertEquals(calculator.getWeekNumber(weekendOrder).getDiscountAmount(), 5 * 2023 * -1);
    }

    @DisplayName("주중일 경우 dessert메뉴 개수 * 2023원 할인")
    @Test
    void weekdayTest() {
        assertEquals(calculator.getWeekNumber(weekdayOrder).getDiscountAmount(), 4 * 2023 * -1);
    }

    @DisplayName("해당 날짜에 대한 크리스마스 디데이 이벤트 할인가 리턴")
    @ParameterizedTest
    @CsvSource(value = {"2:-1100", "3:-1200", "20:-2900", "25:-3400", "31:0"}, delimiter = ':')
    void getXMasDiscount(String input, String expected) {
        assertEquals(calculator.getXMasDiscount(Integer.parseInt(input)).getDiscountAmount(), Integer.parseInt(expected));
    }

    @DisplayName("스페셜 데이에 주문할 경우, 스페셜 데이 할인가 적용 (달력에 별이 붙은 경우)")
    @ParameterizedTest
    @ValueSource(ints = {3, 10, 17, 25})
    void getSpecialDiscount(int day) {
        assertEquals(calculator.getSpecialDiscount(LocalDate.of(2023, 12, day)).getDiscountAmount(), -1000);
    }

    @DisplayName("할인 금액에 따른 Badge 출력")
    @ParameterizedTest
    @CsvSource(value = {"4900:없음", "5600:별", "10200:트리", "23000:산타"}, delimiter = ':')
    void getBadgeWithBenefitAmount(String amount, String expected) {
        assertEquals(calculator.getBadgeWithBenefitAmount(Integer.parseInt(amount)).toString(), expected);
    }
}
