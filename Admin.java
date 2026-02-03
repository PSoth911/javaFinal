
import java.util.Scanner;
import java.util.ArrayList;



public class Admin{

    InsertData data;
    Managestaff manager = new Managestaff();
    Admin(InsertData data, Managestaff manage) {
        this.data = data;
        this.manager = manage;
    }
    Scanner sc = new Scanner(System.in);
    void viewProducts() {
        ArrayList<Product> items = data.getItems();
        PrintData.printItems(items);
    }
    private void adding() {
        System.out.println("Please input product information");

        String category;
        String name;
        int qty;
        double importPrice;
        String importDate;
        double exportPrice;
        String expireDate;

        System.out.print("Category >> ");
        category = sc.next();

        System.out.print("Name >> ");
        name = sc.next();

        System.out.print("Quantity >> ");
        qty = sc.nextInt();

        System.out.print("Import Price >> ");
        importPrice = sc.nextDouble();

        System.out.print("Import Date (D/M/Y) >> ");
        importDate = sc.next();

        System.out.print("Export Price >> ");
        exportPrice = sc.nextDouble();

        System.out.print("Expire Date (D/M/Y) >> ");
        expireDate = sc.next();

        data.addItem(category, name, qty, importPrice, importDate, exportPrice, expireDate);
    }


    void deleting(){
        System.out.println("Please Input the ID of product");
        int value;
        System.out.print("ID >>");
        value = sc.nextInt();
        data.deleteItem(value);
    }

    void increasingByValue(){
        System.out.println("Please Input the ID and Increase value");
        int value;
        int inc;
        System.out.print("ID >>");
        value = sc.nextInt();
        System.out.print("Increase value >>");
        inc = sc.nextInt();
        data.increaseItemByValue(value,inc);
    }

    void decreasingByValue(){
        System.out.println("Please Input the ID and Decrease value");
        int value;
        int dec;
        System.out.print("ID >>");
        value = sc.nextInt();
        System.out.print("Decrease value >>");
        dec = sc.nextInt();
        data.decreaseItemByValue(value,dec);
    }

    void updateStock(){
        int choice;
        do{
            System.out.println(">>>");
            System.out.println("1. Add New Item");
            System.out.println("2. Increase Item's Quantity");
            System.out.println("3. Decrease Item's Quantity");
            System.out.println("4. Delete Item");
            System.out.println("0. Back");
            System.out.print("Enter option: ");
            choice = sc.nextInt();
            switch(choice){
                case 1:
                    System.out.println("\n Add New Item");
                    adding();
                    viewProducts();
                    break;  
                case 2:
                    System.out.println("\n Increase Item's Quantity");
                    increasingByValue();
                    viewProducts();
                    break;  
                case 3:    
                    System.out.println("\n Decrease Item's Quantity");
                    decreasingByValue();
                    viewProducts();
                    break; 
                case 4:
                    System.out.println("\n Delete Item");
                    deleting();
                    viewProducts();
                    break; 
                default:
                    System.out.println("Invalid option!");
            }
        } while (choice!=0);

    }

    void start(){
        int choice;
        System.out.println("\nWelcome Admin\n");
        do {
            System.out.println(">>>");
            System.out.println("1. Check Current Stock");
            System.out.println("2. Update stock");
            System.out.println("3. Manage Staff");
            System.out.println("0. Back");
            System.out.print("Enter option: ");
            choice = sc.nextInt();
            switch(choice) {
                case 1:
                    System.out.println("\n--- Check Current Stock ---");
                    viewProducts();
                    break;  
                case 2:
                    System.out.println("\nUpdate stock");
                    updateStock();
                    break;
                case 3:
                    manager.manageStaffMenu();
                    break;
                case 0:
                    break;
             default:
                    System.out.println("Invalid option! Please choose 0-3.");
            }
            System.out.println();
        
        } while (choice!=0);
    }
}