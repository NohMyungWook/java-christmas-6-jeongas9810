package christmas.common;

import christmas.model.Benefit;
import christmas.model.Order;
import christmas.model.Receipt;
import christmas.model.constant.EventType;
import christmas.view.InputView;
import christmas.view.OutputView;
import christmas.view.constant.Badge;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EventPlanner implements Planner{
    private final static Integer NONE = 0;
    private final static Integer PARTICIPATE_IDX = 0;
    private final static Integer YEAR = 2023;
    private final static Integer MONTH = 12;

    private final OutputView outputView = new OutputView();
    private final InputView inputView = new InputView();
    private final Calculator calculator = new Calculator();

    @Override
    public void start() {
        List<Benefit> benefits;
        Order order = reservation();
        int orderAmount = calculator.getTotalOrderAmount(order.receipts());
        benefits = checkCanParticipate(order, orderAmount);

        if (benefits.get(PARTICIPATE_IDX).getEventType() == EventType.PARTICIPATE) {
            benefits = setBenefits(order, benefits);
            printEvent(benefits, orderAmount);
        }
        if (benefits.get(PARTICIPATE_IDX).getEventType() == EventType.NONE) {
            printEvent(orderAmount);
        }
    }

    @Override
    public Order reservation() {
        outputView.printWelcomeMsg();
        int estimatedDay = inputView.readDate();
        LocalDate date = LocalDate.of(YEAR, MONTH, estimatedDay);
        List<Receipt> receipts = inputView.readOrderMenu();
        outputView.printIntro(estimatedDay);
        return new Order(date, receipts);
    }

    @Override
    public List<Benefit> checkCanParticipate(Order order, int orderAmount) {
        List<Benefit> benefits = new ArrayList<>();

        outputView.printOrderMenu(order.receipts());
        outputView.printAmountBeforeDiscount(orderAmount);

        benefits.add(calculator.canParticipateEvent(orderAmount));
        benefits.add(calculator.canGetGift(orderAmount));

        return benefits;
    }

    @Override
    public List<Benefit> setBenefits(Order order, List<Benefit> benefits) {
        LocalDate date = order.date();
        int dayOfWeekNumber = date.getDayOfWeek().getValue();

        benefits.add(calculator.getWeekNumber(order, dayOfWeekNumber));
        benefits.add(calculator.getXMasDiscount(date.getDayOfMonth()));
        benefits.add(calculator.getSpecialDiscount(date));

        return benefits;
    }

    @Override
    public void printEvent(List<Benefit> benefits, int orderAmount) {
        int benefitAmount = calculator.getTotalDiscountAmount(benefits);
        Badge badge = calculator.getBadgeWithBenefitAmount(benefitAmount);
        List<Receipt> receipts = List.of(new Receipt());

        outputView.printGiftMenu(receipts);
        outputView.printBenefit(benefits);
        outputView.printBenefitAmount(benefitAmount);
        outputView.printAmountAfterDiscount(orderAmount-benefitAmount);
        outputView.printEventBadge(badge);

    }

    @Override
    public void printEvent(int amount) {
        outputView.printGiftMenu(Collections.emptyList());
        outputView.printBenefit(Collections.emptyList());
        outputView.printBenefitAmount(NONE);
        outputView.printAmountAfterDiscount(amount);
        outputView.printEventBadge(Badge.NONE);
    }
}
