package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.model.Receipt;
import christmas.view.constant.ErrorMessage;
import christmas.view.constant.ViewMessage;

import java.util.List;

public class InputView {
    private final static String COMMA = ",";
    private final InputValidator validator = new InputValidator();

    public int readDate() {
        ViewMessage.ASK_DATE.print();

        while (true) {
            try {
                int day = Integer.parseInt(Console.readLine().trim());
                validator.validateDay(day);
                return day;
            } catch (IllegalArgumentException e) {
                ErrorMessage.INPUT_DATE.print();
            }
        }
    }

    public List<Receipt> readOrderMenu() {
        List<Receipt> receipts;

        ViewMessage.ASK_ORDER.print();
        while (true) {
            try {
                String[] menuOrder = Console.readLine().trim().split(COMMA);
                receipts = validator.validateMenuOrder(menuOrder);
                validator.validateReceipt(receipts);
                return receipts;
            } catch (IllegalArgumentException e) {
                ErrorMessage.INPUT_MENU.print();
            }
        }
    }
}
