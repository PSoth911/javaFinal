public class Order {

    private String productName;
    private int quantity;
    private double price;
    private double discount;
    private double totalPrice;

    public Order(String productName, int quantity, double price,double discount) {
        this.productName = productName;
        this.setQuantity(quantity);
        this.setPrice(price);
        this.setDiscount(discount);
        this.setTotalPrice(quantity, price, discount);
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
        return totalPrice;
    }
    public double getDiscount(){
        return discount;
    }


    public void setDiscount(double discount){
        this.discount=discount;
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

    public void setTotalPrice(int quantity,double price,double discount){
        double afterDiscount = price - price*discount;
        double totalPrice=this.quantity*afterDiscount;

        totalPrice=Math.round(totalPrice*100.0)/100.0;

        this.totalPrice=totalPrice;
    }
}
