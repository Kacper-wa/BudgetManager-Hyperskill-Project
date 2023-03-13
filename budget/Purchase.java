package budget;

public class Purchase {
    private String name;
    private double price;
    private double sumProduct = 0.0;

    public Purchase(String name, double price) {
        this.name = name;
        this.price = price;
        sumProduct+= price;
    }

    public String toString() {
        return String.format("%s $%.2f", name, price);
    }

    public double getSumProduct() {
        return sumProduct;
    }

    public String getName() {
        return name;
    }
}