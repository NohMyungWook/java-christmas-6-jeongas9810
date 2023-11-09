package christmas.model;

public class Receipt {
    private final Menu menu;
    private final int count;

    public Receipt(Menu menu, int count) {
        this.menu = menu;
        this.count = count;
    }

    @Override
    public String toString() {
        return menu.getKoName() + " " + count + "개";
    }
}
