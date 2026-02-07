import java.util.ArrayList;

public class Order {
    static int count = 1;
    int orderId;
    double grandTotal;
    ArrayList<OrderProduct> items = new ArrayList<>();

    public Order() {
        orderId = count++;
    }

    public void addItem(OrderProduct item) {
        items.add(item);
        grandTotal += item.total;
    }

    public void printReceipt() {

        System.out.println("\n=========== RECEIPT ==========");
        System.out.println("Order ID: " + orderId);

        System.out.println("--------------------------------");
        System.out.println("Product\tQty\tPrice\tTotal");

        for (OrderProduct item : items) {
            System.out.println(item.productName + "\t" +item.quantity + "\t" +item.price + "\t" +item.total
            );
        }

        System.out.println("--------------------------------");
        System.out.println("Grand Total: $" + grandTotal);
    }
}
