package christmas.common;

import christmas.model.Receipt;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.util.List;

public class EventPlanner implements Planner{
    private OutputView outputView = new OutputView();
    private InputView inputView = new InputView();

    @Override
    public void start() {
        reservation();
    }

    public void reservation() {
        outputView.printWelcomeMsg();
        int estimatedDay = inputView.readDate();
        List<Receipt> receipts = inputView.readOrderMenu();
        outputView.printIntro(estimatedDay);
    }
}
