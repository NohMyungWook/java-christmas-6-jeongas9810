package christmas.common;

import christmas.model.Order;
import christmas.model.Receipt;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.time.LocalDate;
import java.util.List;

public class EventPlanner implements Planner{
    private final OutputView outputView = new OutputView();
    private final InputView inputView = new InputView();

    private final static Integer YEAR = 2023;
    private final static Integer MONTH = 12;

    @Override
    public void start() {
        Order order = reservation();
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
}
