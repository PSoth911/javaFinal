
import java.util.ArrayList;
import java.util.Scanner;
public class Staff {
    ArrayList<StaffAccount> stafflist =new ArrayList<>(); 
    Managestaff manager=new Managestaff();
    Staff(Managestaff manager, ManageProduct data) {
        this.manager = manager;
        this.data = data;
    }
    StaffAccount StaffLogin(){
        System.out.print("Enter The user name to Login: ");
        String UserName=sc.nextLine();
        System.out.print("Enter The Password: ");
        String password=sc.nextLine();
        for(StaffAccount s: manager.Stafflist){
            if(s.username.equals(UserName)&&s.password.equals(password)){
                return s;
            }
        }
        return null;   
    }

    void takeOrder(){
        System.out.print("Enter item name to sell: ");
        String name = sc.next();
        System.out.print("Enter quantity to sell: ");
        int qty = sc.nextInt();
    }

    void sellItem() {
        System.out.print("Enter item name to sell: ");
        String name = sc.next();
        ArrayList<Product> items = data.items;
        boolean found = false;
        for (Product item : items) {
            if (item.name.equalsIgnoreCase(name)) {
                found = true;
                System.out.print("Enter quantity to sell: ");
                int qty = sc.nextInt();

                if (qty <= item.quantity) {
                    item.quantity -= qty;
                    double totalPrice = qty * item.exportPrice;
                    System.out.println("Sold " + qty + " " + item.name + "(s). Total: $" + totalPrice);
                } else {
                    System.out.println("Not enough stock! Current stock: " + item.quantity);
                }
                break;
            }
        }
        if (!found) {
            System.out.println("Item not found!");
        }
    }

    Scanner sc = new Scanner(System.in);
    ManageProduct data;
    void viewProducts() {
        ArrayList<Product> items = data.items;
        PrintProduct.printItems(items);
    }
    void start() {
        int choice;
        StaffAccount loggedIn = StaffLogin();
        if (loggedIn == null) {
            System.out.println("Invalid username or password!");
            return;
        }
        System.out.println("Login success!");
        System.out.println("Welcome " + loggedIn.username);
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
                default:
                    System.out.println("Invalid option! Please choose 0-4.");
                    break;
            }
            System.out.println();

        } while (choice != 0);
    }
}
