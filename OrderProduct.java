public class OrderProduct {
    String productName;
    int quantity;
    double price;
    double total;

    public OrderProduct(String productName, int quantity, double price) {
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.total = quantity * price;
    }
}
