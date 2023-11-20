package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.model.Receipt;
import christmas.view.constant.ErrorMessage;
import christmas.view.constant.ViewMessage;

import java.util.ArrayList;
import java.util.List;

public class InputView {
    private final static int INIT_INT = 0;
    private final static String COMMA = ",";
    private final InputValidator validator = new InputValidator();

    public int readDate() {
        ViewMessage.ASK_DATE.print();
        int day = INIT_INT;

        while (day == INIT_INT) {
            try {
                day = Integer.parseInt(Console.readLine().trim());
                validator.validateDay(day);
            } catch (IllegalArgumentException e) {
                ErrorMessage.INPUT_DATE.print();
                day = INIT_INT;
            }
        }
        return day;
    }

    public List<Receipt> readOrderMenu() {
        boolean isValidated = false;
        List<Receipt> receipts = new ArrayList<>();

        ViewMessage.ASK_ORDER.print();
        while (!isValidated) {
            try {
                String[] menuOrder = Console.readLine().trim().split(COMMA);
                receipts = validator.validateMenuOrder(menuOrder);
                validator.validateReceipt(receipts);
                isValidated = true;
            } catch (IllegalArgumentException e) {
                ErrorMessage.INPUT_MENU.print();
            }
        }
        return receipts;
    }
}
