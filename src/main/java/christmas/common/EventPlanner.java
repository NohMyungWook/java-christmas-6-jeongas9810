package christmas.common;

import christmas.model.Benefit;
import christmas.model.Menu;
import christmas.model.Order;
import christmas.model.Receipt;
import christmas.model.constant.EventType;
import christmas.model.constant.MenuItems;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EventPlanner implements Planner{
    private final OutputView outputView = new OutputView();
    private final InputView inputView = new InputView();
    private final Calculator calculator = new Calculator();

    private final static Integer YEAR = 2023;
    private final static Integer MONTH = 12;
    private final static Integer CAN_PARTICIPATE_AMOUNT = 100000;
    private final static Integer GET_GIFT_AMOUNT = 120000;

    @Override
    public void start() {
        Order order = reservation();
        List<Benefit> benefits = checkCanParticipate(order);
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

        if (amount < CAN_PARTICIPATE_AMOUNT) {
            benefits.add(new Benefit(EventType.NONE));
        }
        return benefits;
    }
}
