package christmas.view;

import christmas.model.Benefit;
import christmas.model.Receipt;
import christmas.model.constant.EventType;
import christmas.view.constant.Badge;
import christmas.view.constant.ViewMessage;

import java.util.List;

public class OutputView {
    public void printWelcomeMsg() {
        ViewMessage.WELCOME.print();
    }

    public void printIntro(int day) {
        ViewMessage.INTRODUCE.renderAndPrint(day);
        ViewMessage.PRINT_LINE.print();
    }

    public void printOrderMenu(List<Receipt> receipt) {
        ViewMessage.ORDER.print();
        printReceipt(receipt);
        ViewMessage.PRINT_LINE.print();
    }

    public void printAmountBeforeDiscount(int amount) {
        ViewMessage.AMOUNT_BEFORE_DISCOUNT.print();
        printAmount(amount);
    }

    public void printGiftMenu(List<Receipt> receipt) {
        ViewMessage.GIFT_MENU.print();
        if (receipt.isEmpty()) {
            ViewMessage.NONE.print();
            ViewMessage.PRINT_LINE.print();
            return;
        }
        printReceipt(receipt);
        ViewMessage.PRINT_LINE.print();
    }

    public void printBenefit(List<Benefit> benefits) {
        ViewMessage.BENEFITS.print();
        if (benefits.isEmpty()) {
            ViewMessage.NONE.print();
            ViewMessage.PRINT_LINE.print();
            return;
        }

        for (Benefit benefit : benefits) {
            if(benefit.getEventType() == EventType.PARTICIPATE) continue;
            ViewMessage.BENEFIT.renderAndPrint(benefit.getEventType().toString(), benefit.getPrice());
            ViewMessage.PRINT_LINE.print();
        }
        ViewMessage.PRINT_LINE.print();
    }

    public void printBenefitAmount(int amount) {
        ViewMessage.BENFIT_AMOUNT.print();
        printAmount(amount);
    }

    public void printAmountAfterDiscount(int amount) {
        ViewMessage.AMOUNT_AFTER_DISCOUNT.print();
        printAmount(amount);
    }

    public void printEventBadge(Badge badge) {
        ViewMessage.BADGE.print();
        ViewMessage.PRINT_MESSAGE.renderAndPrint(badge.toString());
    }

    private void printReceipt(List<Receipt> receipts) {
        for (Receipt receipt : receipts) {
            ViewMessage.RECEIPT.renderAndPrint(receipt.getKoName(), receipt.getCount());
            ViewMessage.PRINT_LINE.print();
        }
    }

    private void printAmount(int amount) {
        ViewMessage.AMOUNT.renderAndPrint(amount);
        ViewMessage.PRINT_LINE.print();
    }
}
