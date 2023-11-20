package christmas.view;

import christmas.model.constant.MenuType;
import christmas.model.Receipt;
import christmas.view.constant.ErrorMessage;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class InputValidator {
    private static final int INIT_INT = 0;
    private static final int FIRST_DAY = 1;
    private static final int LAST_DAY = 31;
    private static final String BLANK = " ";
    private static final String SEPARATOR = "-";
    private static final String REGEX_MENU_AND_COUNT = "^[가-힣]+-[0-9]+$";
    private static final int KOR_NAME_IDX = 0;
    private static final int COUNT_IDX = 1;
    private static final String NONE = "none";
    private static final int MAX_MENU_COUNT = 20;

    public void validateDay(int day) {
        if (day < FIRST_DAY || day > LAST_DAY) {
            throw new IllegalArgumentException();
        }
    }

    public List<Receipt> validateMenuOrder(String[] menuOrder) {
        List<Receipt> receipts = new ArrayList<>();
        String[] menuAndCount;
        for(String menuItemInOrder : menuOrder) {
            validateContainsBlank(menuItemInOrder);
            validateInputFormat(menuItemInOrder);

            menuAndCount = menuItemInOrder.split(SEPARATOR);
            receipts.add(new Receipt(menuAndCount[KOR_NAME_IDX], Integer.parseInt(menuAndCount[COUNT_IDX])));
        }
        return receipts;
    }

    private void validateContainsBlank(String menuItem) {
        if (menuItem.contains(BLANK)) {
            throw new IllegalArgumentException();
        }
    }

    private void validateInputFormat(String menuItem) {
        if (!menuItem.matches(REGEX_MENU_AND_COUNT)) {
            throw new IllegalArgumentException();
        }
    }

    public void validateReceipt(List<Receipt> receipts) {
        int totalCount = INIT_INT, notDrinkCount = INIT_INT;

        for (Receipt receipt : receipts) {
            validateMenuExistInMenuBoard(receipt);
            if (validateMenuType(receipt)) {
                notDrinkCount ++;
            }
            totalCount += receipt.getCount();
        }

        if (notDrinkCount == INIT_INT) {
            ErrorMessage.CANNOT_ORDER_ONLY_DRINK.print();
            throw new IllegalArgumentException();
        }
        validateTotalCount(totalCount);
    }

    private boolean validateMenuType(Receipt receipt) {
        return receipt.getMenu().menuType() != MenuType.DRINK;
    }

    private void validateMenuExistInMenuBoard(Receipt receipt)  {
        if (Objects.equals(receipt.getMenu().koName(), NONE)) {
            throw new IllegalArgumentException();
        }
    }

    private void validateTotalCount(int totalCount) {
        if (totalCount > MAX_MENU_COUNT) {
            ErrorMessage.MAX_MENU_COUNT.print();
            throw new IllegalArgumentException();
        }
    }
}
