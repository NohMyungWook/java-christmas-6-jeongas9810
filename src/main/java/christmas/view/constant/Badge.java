package christmas.view.constant;

public enum Badge {
    NONE("없음", 0), STAR("별", 5000), TREE("트리", 10000), SANTA("산타", 20000);

    private final String badgeName;
    private final Integer price;

    Badge(String badgeName, int price) {
        this.badgeName = badgeName;
        this.price = price;
    }

    public Integer getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return badgeName;
    }
}
