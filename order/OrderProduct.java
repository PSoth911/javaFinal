package order;
public class OrderProduct {

    private String productName;
    private int quantity;
    private double price;
    private double total;

    public OrderProduct(String productName, int quantity, double price) {
        this.productName = productName;
        setQuantity(quantity);
        setPrice(price);
        this.total = this.quantity * this.price;
    }

    public String getProductName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public double getTotal() {
        return total;
    }

    public void setQuantity(int quantity) {
        if (quantity > 0) {
            this.quantity = quantity;
        }
    }

    public void setPrice(double price) {
        if (price > 0) {
            this.price = price;
        }
    }
}
