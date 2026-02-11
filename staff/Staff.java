package staff;

import java.util.ArrayList;
import java.util.Scanner;

import order.Order;
import order.OrderProduct;
import product.ManageProduct;
import product.Product;
public class Staff {
    public ArrayList<StaffAccount> stafflist =new ArrayList<>(); 
    private Managestaff manager=new Managestaff();
    private Order currentOrder = new Order();
    public Staff(Managestaff manager, ManageProduct data) {
        this.manager = manager;
        this.data = data;
    }
    public StaffAccount StaffLogin(){
        System.out.print("Enter The user name to Login: ");
        String UserName=sc.nextLine();
        System.out.print("Enter The Password: ");
        String password=sc.nextLine();
        for(StaffAccount s: manager.Stafflist){
            if(s.getUsername().equals(UserName)&&s.checkPassword(password)){
                return s;
            }
        }
        return null;   
    }

    public void sellItem() {
        System.out.print("Enter item name to sell: ");
        String name = sc.next();
        ArrayList<Product> items = data.items;
        boolean found = false;
        for (Product item : items) {
            if (item.getName().equalsIgnoreCase(name)) {
                found = true;
                System.out.print("Enter quantity to sell: ");
                int qty = sc.nextInt();

                if (qty <= item.getQuantity()) {
                    item.setQuantity(item.getQuantity() - qty);
                    double totalPrice = qty * item.getExportPrice();
                    System.out.println("Sold " + qty + " " + item.getName() + "(s). Total: $" + totalPrice);
                    currentOrder.addItem(new OrderProduct(item.getName(), qty, item.getExportPrice()));
                } else {
                    System.out.println("Not enough stock! Current stock: " + item.getQuantity());
                }
                break;
            }
        }
        if (!found) {
            System.out.println("Item not found!");
        }
    }

    Scanner sc = new Scanner(System.in);
    private ManageProduct data=new ManageProduct();
    void viewProducts() {
        ArrayList<Product> items = data.items;
        ManageProduct.printItems(items);
    }
    public void start() {
        int choice;
        StaffAccount loggedIn = StaffLogin();
        if (loggedIn == null) {
            System.out.println("Invalid username or password!");
            return;
        }
        System.out.println("Login success!");
        System.out.println("Welcome " + loggedIn.getUsername());
        do {
            System.out.println(">>>");
            System.out.println("1. View Items");
            System.out.println("2. Sell Items");
            System.out.println("3. Update Stock");
            System.out.println("4. View Receipt");
            System.out.println("0. Back");
            System.out.print("Enter option: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("\n================= Current Stock ====================");
                    viewProducts();
                    break;
                case 2:
                    sellItem();
                    break;
                case 3:
                    System.out.println("Update stock feature not implemented yet.");
                    break;
                case 4:
                    currentOrder.printReceipt();
                    break;
                default:
                    System.out.println("Invalid option! Please choose 0-4.");
                    break;
            }
            System.out.println();

        } while (choice != 0);
    }
}
