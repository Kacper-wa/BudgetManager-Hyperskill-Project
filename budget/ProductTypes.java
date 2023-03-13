package budget;

public enum ProductTypes {
    FOOD("Food"),
    CLOTHES("Clothes"),
    ENTERTAINMENT("Entertainment"),
    OTHER("Other"),
    ALL("All");

    private final String name;

    ProductTypes(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}