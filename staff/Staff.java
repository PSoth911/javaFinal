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
    private ArrayList<Order> allOrders = new ArrayList<>();
    private Order currentOrder = null;
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
        if (currentOrder == null) {
        currentOrder = new Order(); 
    }
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
                    double afterDiscount = item.getExportPrice() - (item.getDiscount()*item.getExportPrice());
                    double totalPrice = Math.round(qty * afterDiscount*100.0)/100.0;
                    System.out.println("Discount : " + item.getDiscount()*100 +"%");
                    System.out.println("Sold " + qty + " " + item.getName() + "(s). Total: $" + totalPrice);
                    if (currentOrder == null) {
                    currentOrder = new Order();
                    }
                    currentOrder.addItem(new OrderProduct(item.getName(), qty, item.getExportPrice(),item.getDiscount()));
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
    public void completeOrder() {
        if (currentOrder != null && currentOrder.getGrandTotal() > 0) {
            allOrders.add(currentOrder);
            System.out.println("\nOrder completed! Receipt:");
            currentOrder.printReceipt();
            currentOrder = null;
        } else {
            System.out.println("No items sold in this order.");
        }
    }
    public void printAllReceipts() {
        if (allOrders.isEmpty()) {
            System.out.println("No orders have been sold yet.");
            return;
        }

        System.out.println("\n========== ALL SOLD ORDERS ==========");
        for (Order order : allOrders) {
            order.printReceipt();
        }
    }


    private void ManageAccount(StaffAccount account){
        int choice=0;
        do {
            System.out.println(">>>");
            System.out.println("1. Change Username");
            System.out.println("2. Change password");
            System.out.println("3. Change PhoneNUmber");
            System.out.println("4. Change Email");
            System.out.println("0. Exit ...! ");
            System.out.print("Enter Option to continuse:");
            choice=sc.nextInt();
            sc.nextLine();
        switch (choice) {
            case 0:
                System.out.println("Exit......!");
                break;
             case 1:
                System.out.println("Enter your Old username:");
                String username= sc.nextLine();
                System.out.println("Enter Password:");
                String password=sc.nextLine();
                System.out.print("Enter new username: ");
                String newUsername = sc.nextLine();
                account.setUsername(newUsername,username,password);
                break;

            case 2:
                System.out.print("Enter old password: ");
                String oldPass = sc.nextLine();

                System.out.print("Enter new password: ");
                String newPass = sc.nextLine();
                account.setpassword(oldPass, newPass);
                break;

            case 3:
                System.out.print("Enter new phone number: ");
                String newPhone = sc.nextLine();

                System.out.println("Enter password:");
                String Ppassword=sc.nextLine();
                account.setPhoneNumber(newPhone,Ppassword);
                System.out.println("Phone updated.");
                break;

            case 4:
                System.out.print("Enter new email: ");
                String newEmail = sc.nextLine();

                System.out.println("Enter password:");
                String Epassword=sc.nextLine();
                account.setEmail(newEmail,Epassword);
                System.out.println("Email updated.");
                break;   
            default:
                System.out.println("Invalid Option>>...!");
                break;
        }

            

        } while (choice!=0);
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
            System.out.println("4. Complete order");
            System.out.println("5. View all Receipt");
            System.out.println("6. Manage account");
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
                    completeOrder();
                    break;
                case 5:
                    printAllReceipts();
                    break;
                case 6: 
                    ManageAccount(loggedIn);
                    break;
                default:
                    System.out.println("Invalid option! Please choose 0-4.");
                    break;
            }
            System.out.println();

        } while (choice != 0);
    }
}
