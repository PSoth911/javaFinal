package order;
import java.util.ArrayList;

public class Order {

    private static int count = 1;
    private int orderId;
    private double grandTotal;
    private ArrayList<OrderProduct> items;

    public Order() {
        this.orderId = count++;
        this.items = new ArrayList<>();
        this.grandTotal = 0;
    }

    public static int getTotalOrders() {
        return count - 1;
    }

    public void addItem(OrderProduct item) {
        if (item != null) {
            items.add(item);
            grandTotal += item.getTotal();
        }
    }

    public void printReceipt() {
        System.out.println("\n=========== RECEIPT ==========");
        System.out.println("Order ID: " + orderId);
        System.out.println("--------------------------------");
        System.out.println("Product\tQty\tPrice\tDiscount\tTotal");

        for (OrderProduct item : items) {
            System.out.println(item.getProductName() + "\t" +item.getQuantity() + "\t" +item.getPrice()+ "\t" +item.getDiscount() + "\t\t" +item.getTotal()
            );
        }

        System.out.println("--------------------------------");
        System.out.println("Grand Total: $" + grandTotal);
    }

    public double getGrandTotal() {
        return grandTotal;
    }
}
