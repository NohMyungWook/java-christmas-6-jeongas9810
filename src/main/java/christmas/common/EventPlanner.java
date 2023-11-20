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

public class EventPlanner{
    private final static Integer NONE = 0;
    private final static Integer PARTICIPATE_IDX = 0;
    private final static Integer YEAR = 2023;
    private final static Integer MONTH = 12;

    private final OutputView outputView = new OutputView();
    private final InputView inputView = new InputView();
    private final Calculator calculator = new Calculator();

    public void start() {
        Order order = reservation();
        int orderAmount = calculator.getTotalOrderAmount(order.receipts());
        printOrderMenuAndAmount(order, orderAmount);
        List<Benefit> benefits = makeBenefits(order, orderAmount);

        if (benefits.get(PARTICIPATE_IDX).getEventType() == EventType.PARTICIPATE) {
            printEvent(benefits, orderAmount);
            return;
        }
        printEvent(orderAmount);
    }

    public Order reservation() {
        outputView.printWelcomeMsg();
        int estimatedDay = inputView.readDate();
        List<Receipt> receipts = inputView.readOrderMenu();
        outputView.printIntro(estimatedDay);
        return new Order(LocalDate.of(YEAR, MONTH, estimatedDay), receipts);
    }

    public void printOrderMenuAndAmount(Order order, int orderAmount) {
        outputView.printOrderMenu(order.receipts());
        outputView.printAmountBeforeDiscount(orderAmount);
    }

    public List<Benefit> makeBenefits(Order order, int orderAmount) {
        List<Benefit> benefits = new ArrayList<>();

        benefits.add(calculator.canParticipateEvent(orderAmount));
        benefits.add(calculator.canGetGift(orderAmount));

        if (benefits.get(PARTICIPATE_IDX).getEventType() == EventType.PARTICIPATE) {
            setBenefits(order, benefits);
        }
        return benefits;
    }

    public List<Benefit> setBenefits(Order order, List<Benefit> benefits) {
        LocalDate date = order.date();

        benefits.add(calculator.getWeekNumber(order));
        benefits.add(calculator.getXMasDiscount(date.getDayOfMonth()));
        benefits.add(calculator.getSpecialDiscount(date));

        return benefits;
    }

    public void printEvent(List<Benefit> benefits, int orderAmount) {
        int benefitAmount = calculator.getTotalDiscountAmount(benefits);
        Badge badge = calculator.getBadgeWithBenefitAmount(benefitAmount);
        List<Receipt> giftReceipt = List.of(new Receipt());

        outputView.printGiftMenu(giftReceipt);
        outputView.printBenefit(benefits);
        outputView.printBenefitAmount(benefitAmount);
        outputView.printAmountAfterDiscount(orderAmount-benefitAmount);
        outputView.printEventBadge(badge);

    }
    
    public void printEvent(int amount) {
        outputView.printGiftMenu(Collections.emptyList());
        outputView.printBenefit(Collections.emptyList());
        outputView.printBenefitAmount(NONE);
        outputView.printAmountAfterDiscount(amount);
        outputView.printEventBadge(Badge.NONE);
    }
}
