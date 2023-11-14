package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.model.Receipt;
import christmas.view.constant.ErrorMessage;
import christmas.view.constant.ViewMessage;

import java.util.ArrayList;
import java.util.List;

public class InputView {
    private final InputValidator validator = new InputValidator();

    public int readDate() {
        ViewMessage.ASK_DATE.print();
        int day = 0;

        while (day == 0) {
            try {
                day = Integer.parseInt(Console.readLine().trim());
                validator.validateDay(day);
            } catch (NumberFormatException e) {
                ErrorMessage.ERR_INPUT_NUMBER.print();
            } catch (IllegalArgumentException e) {
                ErrorMessage.ERR_INPUT_DATE.print();
                day = 0;
            }
        }
        return day;
    }

    public List<Receipt> readOrderMenu() {
        List<Receipt> receipts = new ArrayList<>();
        ViewMessage.ASK_ORDER.print();

        while (receipts.isEmpty()) {
            try {
                String[] menuOrder = Console.readLine().trim().split(",");
                receipts = validator.validateMenuOrder(menuOrder);
                validator.validateReceipt(receipts);
            } catch (IllegalArgumentException e) {
                ErrorMessage.ERR_INPUT_MENU.print();
            }
        }
        return receipts;
    }
}
