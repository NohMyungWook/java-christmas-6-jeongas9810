package christmas.view;

import christmas.model.Benefit;
import christmas.model.Receipt;
import christmas.view.constant.Badge;
import christmas.view.constant.ViewMessage;

import java.util.List;

public class OutputView {
    public void printWelcomeMsg() {
        ViewMessage.MSG_WELCOME.print();
    }

    public void printIntro(int day) {
        ViewMessage.MSG_INTRO.renderAndPrint(day);
        ViewMessage.PRINT_LINE.print();
    }

    public void printOrderMenu(List<Receipt> receipt) {
        ViewMessage.MSG_ORDER_INTRO.print();
        printReceipt(receipt);
        ViewMessage.PRINT_LINE.print();
    }

    public void printAmountBeforeDiscount(int amount) {
        ViewMessage.MSG_AMOUNT_BEFORE_DISCOUNT.print();
        printAmount(amount);
    }

    public void printGiftMenu(List<Receipt> receipt) {
        ViewMessage.MSG_GIFT_MENU.print();
        if (receipt.isEmpty()) {
            ViewMessage.MSG_NONE.print();
            ViewMessage.PRINT_LINE.print();
            return;
        }
        printReceipt(receipt);
        ViewMessage.PRINT_LINE.print();
    }

    public void printBenefit(List<Benefit> benefits) {
        ViewMessage.MSG_BENEFITS.print();
        if (benefits.isEmpty()) {
            ViewMessage.MSG_NONE.print();
            ViewMessage.PRINT_LINE.print();
            return;
        }

        for (Benefit benefit : benefits) {
            ViewMessage.PRINT_MSG.renderAndPrint(benefit.toString());
        }
        ViewMessage.PRINT_LINE.print();
    }

    public void printBenefitAmount(int amount) {
        ViewMessage.MSG_BENFIT_AMOUNT.print();
        printAmount(amount);
    }

    public void printAmountAfterDiscount(int amount) {
        ViewMessage.MSG_AMOUNT_AFTER_DISCOUNT.print();
        printAmount(amount);
    }

    public void printEventBadge(Badge badge) {
        ViewMessage.MSG_BADGE.print();
        ViewMessage.PRINT_MSG.renderAndPrint(badge.toString());
    }

    private void printReceipt(List<Receipt> receipts) {
        for (Receipt receipt : receipts) {
            ViewMessage.PRINT_MSG.renderAndPrint(receipt.toString());
        }
    }

    private void printAmount(int amount) {
        ViewMessage.MSG_AMOUNT.renderAndPrint(amount);
        ViewMessage.PRINT_LINE.print();
    }
}
