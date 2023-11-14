package christmas.common;

import christmas.model.Benefit;
import christmas.model.Order;
import christmas.model.Receipt;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EventPlanner implements Planner{

    private final static Integer YEAR = 2023;
    private final static Integer MONTH = 12;

    private final OutputView outputView = new OutputView();
    private final InputView inputView = new InputView();
    private final Calculator calculator = new Calculator();

    @Override
    public void start() {
        Order order = reservation();
        List<Benefit> benefits = checkCanParticipate(order);
        benefits = setBenefits(order, benefits);
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
    public List<Benefit> checkCanParticipate(Order order) {
        List<Benefit> benefits = new ArrayList<>();
        int amount = calculator.getTotalAmount(order.getReceipts(), benefits);

        outputView.printOrderMenu(order.getReceipts());
        outputView.printAmountBeforeDiscount(amount);

        benefits.add(calculator.canParticipateEvent(amount));
        benefits.add(calculator.canGetGift(amount));

        return benefits;
    }

    public List<Benefit> setBenefits(Order order, List<Benefit> benefits) {
        LocalDate date = order.getDate();
        int dayOfWeekNumber = date.getDayOfWeek().getValue();

        benefits.add(calculator.getWeekNumber(order, dayOfWeekNumber));
        benefits.add(calculator.getXMasDiscount(date.getDayOfMonth()));
        benefits.add(calculator.getSpecialDiscount(date));

        return benefits;
    }
}
