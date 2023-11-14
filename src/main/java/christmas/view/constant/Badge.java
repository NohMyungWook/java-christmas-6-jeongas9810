package christmas.view.constant;

public enum Badge {
    NONE("없음"), STAR("별"), TREE("트리"), SANTA("산타");

    private final String badgeName;

    Badge(String badgeName) {
        this.badgeName = badgeName;
    }

    @Override
    public String toString() {
        return badgeName;
    }
}
