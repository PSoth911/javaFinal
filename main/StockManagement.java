package main;

import java.util.ArrayList;
import java.util.Scanner;

import model.Order;
import model.Product;
import user.Cashier;
import user.IStaff;
import user.Manager;
import user.Staff;
import user.Stocker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class StockManagement {
    Scanner sc = new Scanner(System.in);
    

    public static final String VIEW_PRODUCTS = "View Products";
    public static final String SELL_PRODUCT = "Sell Product";
    public static final String UPDATE_STOCK = "Update Stock";
    public static final String VIEW_RECIPT = "View Receipt";
    public static final String MANAGE_ACCOUNT = "Manage account";
    public static final String COMPLETE_ORDER = "Complete order";

    private String shopName;
    private String address;
    ArrayList<Product> products;
    ArrayList<IStaff> staffs;
    ArrayList<Order> orders;
    private Order currentOrder = null;
    private IStaff currentUser;

    public StockManagement(String shopName, String address) {
        this.setShopName(shopName);
        this.setAddress(address);

        products = new ArrayList<>();
        staffs = new ArrayList<>();
        orders = new ArrayList<>();

        
        setDefaultData();
    }


    private void setDefaultData(){
        Manager defaultAdmin = new Manager(new Staff("admin123", "Admin@123", "0123456789", "admin@gmail.com", LocalDate.now().toString()), 1500);
        staffs.add(defaultAdmin);

        Cashier defaultCashier = new Cashier(new Staff("Cashier123", "Cashier@123", "0123456789", "admin@gmail.com", LocalDate.now().toString()), 500);
        staffs.add(defaultCashier);

        Stocker defaultStocker = new Stocker(new Staff("Cashier123", "Cashier@123", "0123456789", "admin@gmail.com", LocalDate.now().toString()), 500);
        staffs.add(defaultStocker);

        products.add(new Product("Food","Bread",100,0.5,LocalDate.now(),1.0,LocalDate.now().plusDays(7)));
        products.add(new Product("Drink","Water",200,0.2,LocalDate.now(),0.5,LocalDate.now().plusDays(30)));
        products.add(new Product("Food","Cake",50,1.0,LocalDate.now(),2.0,LocalDate.now().plusDays(3)));
        products.add(new Product("Food","Milk",80,0.8,LocalDate.now(),1.5,LocalDate.now().plusDays(5)));
        products.add(new Product("Drink","Juice",120,0.3,LocalDate.now(),0.8,LocalDate.now().plusDays(20)));
    }

    

    public String getShopName() {
        return shopName;
    }
    public String getAddress() {
        return address;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }
    public void setAddress(String address) {
        this.address = address;
    }


    // Product Management
    public void addItem(String category,String name,int quantity,double importPrice,String importDateStr,double exportPrice,String expiredDateStr) {
        DateTimeFormatter convert = DateTimeFormatter.ofPattern("d/M/yyyy");
        LocalDate importDate = LocalDate.parse(importDateStr,convert);
        LocalDate expiredDate = LocalDate.parse(expiredDateStr,convert);
        products.add(new Product(category, name, quantity, importPrice, importDate, exportPrice, expiredDate));
    }

    public void deleteItem(int id){
       for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId()== id) {
                products.remove(i);
                break;
            }
        }
    }

    public void increaseItemByValue(int id,int value){
       for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId()== id) {
                products.get(i).setQuantity(products.get(i).getQuantity() + value);
                break;
            }
        }
    }
    public void decreaseItemByValue(int id,int value){
       for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId()== id) {
                if (products.get(i).getQuantity()>=value){
                    products.get(i).setQuantity(products.get(i).getQuantity() - value);
                    break;
                }else if(products.get(i).getQuantity()==0){
                    System.out.println("Item is zero");
                }else{
                    System.out.println("Item's quantity < Decrease");
                }
            }
        }
    }

    public void increaseItem(int id){
       for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId()== id) {
                products.get(i).setQuantity(products.get(i).getQuantity() + 1);
                break;
            }
        }
    }
    public void decreaseItem(int id){
       for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId()== id) {
                if (products.get(i).getQuantity()>0){
                    products.get(i).setQuantity(products.get(i).getQuantity() - 1);
                    break;
                }else{
                    System.out.println("Item's quantity is 0");
                    break;
                }
            }
        }
    }

    public static void printItems(ArrayList<Product> items){
        System.out.println("ID\tCategory\tName\tQuantity\tImport Price\tImport Date\tExport Price\t\tExpired Date");
        for (Product item : items){
            System.out.println(item.getId() + "\t" +item.getCategory() + "\t\t" + item.getName() + "\t" + item.getQuantity()+ "\t\t" + item.getImportPrice()+ "\t\t" +item.getImportDate()+ "\t\t" + item.getExportPrice() + "\t\t" + item.getExpiredDate());
        }
    }

    // Staff Management
    private void removeStaff(){
            currentUser = login();
            ShowStaffList();
            System.out.println("Enter THe StaffID to Update:");
            int id=sc.nextInt();
            sc.nextLine();
            IStaff s = findStaffById(id);
            if (s == currentUser) {
                System.out.println("You cannot remove your own account!");
                return;
            }
            if (s != null) {
                System.out.println("Are you sure you want to remove " + s.getUsername()
                + "? (Y/N)");
                String confirm = sc.nextLine();
                    if (confirm.equalsIgnoreCase("Y")) {
                        staffs.remove(s);
                        System.out.println("Staff removed successfully!");
                    } else {
                        System.out.println("Remove cancelled.");
                    }
            }else{
                System.out.println("Staff is not FOund....!");
            }

        
    }


    private void updateStaff(){
        ShowStaffList();
        System.out.println("Enter THe StaffID to Update:");
        int id=sc.nextInt();
        sc.nextLine();
        IStaff s = findStaffById(id);

        if (s != null) {
            System.out.print("New username: ");
            String username = sc.nextLine();

            System.out.print("Enter oldUsername");
            String oldusername=sc.nextLine();

            System.out.print("Enter password of this staff");
            String password= sc.nextLine();


            System.out.print("New phone number: ");
            String phone = sc.nextLine();

            System.out.print("New email: ");
            String email = sc.nextLine();

            if (!validstaffinput(username,password, phone, email)) {
                System.out.println("Update failed due to invalid input.");
                return;
             }
            s.setUsername(username,oldusername,password);
            s.setPhoneNumber(phone,password);
            s.setEmail(email,password);
            System.out.print("Enter new password: ");
            String newPass = sc.nextLine();

            s.setpassword(password, newPass);
            System.out.println("staff updated successfully!");
        } else {
        System.out.println("staff not found!");
        }
    }



    private IStaff findStaffById(int id){
        for(int i=0; i<staffs.size(); i++){
        if(staffs.get(i).getId() == id){
            return staffs.get(i);
        }
     }
        return null;
    }


    private void SearchStaff(){
        boolean check=false;
        System.out.println("Enter UserName staff That you Want to search:");
        String name=sc.nextLine();
        System.out.println("Enter Phone Number staff That you Want to search:");
        String phone=sc.nextLine();
        for(int i=0;i<staffs.size();i++){
            if(staffs.get(i).getUsername().equalsIgnoreCase(name)&&staffs.get(i).getPhoneNumber().equals(phone)){
                System.out.println("----------------------------------------------------------------------------------");
                System.out.println("ID\tUsername\t\tPhone\t\t\tEMAIL");
                System.out.println(staffs.get(i).getId() + "\t" 
                               +staffs.get(i).getUsername() + "\t\t" 
                               +staffs.get(i).getPhoneNumber() + "\t\t" 
                               +staffs.get(i).getEmail());
                               check=true;
            }
            if(check!=true){
                System.out.println("Staff not found...!");
            }
        }
        
    }



    private boolean validstaffinput(String username, String password,String phoneNumber ,String email){
        if (!username.matches("^[A-Za-z0-9]{5,}$")) {
            System.out.println("ERROR: Username must be at least 5 characters (letters and numbers only).");
            return false;
        }
        if (!password.matches("^(?=.*[A-Za-z])(?=.*\\d).{8,}$")) {
            System.out.println("ERROR: Password must be at least 8 characters and include letters and numbers.");
            return false;
        }
        if (!phoneNumber.matches("^\\d{8,}$")) {
            System.out.println("ERROR: Phone number must be at least 8 digits.");
            return false;
        }
        if (!email.matches("^[A-Za-z0-9._%+-]+@gmail\\.com$")) {
            System.out.println("ERROR: Email must be a valid Gmail address.");
            return false;
        }
        return true;
    }



    private void ShowStaffList() {
        System.out.println("------------------------------------Staff Dashbaord-------------------------------");
        System.out.println("ID\tUsername\t\tPhone\t\t\tEMAIL\t\t\tPosition");
        System.out.println("----------------------------------------------------------------------------------");
        for (int i=0;i<staffs.size();i++) {
            System.out.println(staffs.get(i).getId() + "\t" 
                               +staffs.get(i).getUsername() + "\t\t" 
                               +staffs.get(i).getPhoneNumber() + "\t\t" 
                               +staffs.get(i).getEmail());
        }
    }

    public void createStaff (){
        System.out.print("Enter Username: ");
        String username = sc.nextLine();
        System.out.print("Enter Password: ");
        String password = sc.nextLine();
        System.out.print("Enter Phone Number: ");
        String phoneNumber = sc.nextLine();
        System.out.print("Enter Email: ");
        String email= sc.nextLine();
        System.out.print("Enter Position: ");
        String position = sc.nextLine();    
        if (!validstaffinput(username, password, phoneNumber, email)) {
            System.out.println("Staff NOT added because invalid input.");
            return;
        }
        for(int i=0;i<staffs.size();i++){
            if(username.equalsIgnoreCase(staffs.get(i).getUsername())){
                System.out.println("This staff already exits...!");
                return;
            }
        }
        if (position.equalsIgnoreCase("Manager")) {
            staffs.add(new Manager(username, password, phoneNumber, email, position));
            System.out.println("New Manager Add Sucessfully...!"); 
        }else if(position.equalsIgnoreCase("Cashier")){
            staffs.add(new Cashier(username, password, phoneNumber,email,LocalDate.now().toString(),position));
            System.out.println("New Cashier Add Sucessfully...!"); 
        }else if(position.equalsIgnoreCase("Stocker")){
            staffs.add(new Stocker(username, password, phoneNumber, email, LocalDate.now().toString(), position));
            System.out.println("New Stocker Add Sucessfully...!"); 
        }
        
    }


    // Order Management

    public void sellItem() {
        System.out.print("Enter item name to sell: ");
        String name = sc.next();
        boolean found = false;
        for (Product item : products) {
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
                    Order orderitem = new Order(item.getName(), qty, item.getExportPrice(), item.getDiscount());
                    if (currentOrder == null) {
                    currentOrder = new Order();
                    }
                    currentOrder.addItem(orderitem);
                    System.out.println("Item added to current order.");
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
        if (currentOrder != null && !currentOrder.getItems().isEmpty()) {
            orders.add(currentOrder);
            System.out.println("\nOrder completed! Receipt:");
            printReceipt(currentOrder);
            currentOrder = null;
        } else {
            System.out.println("No items sold in this order.");
        }
    }
    
    public void printReceipt(Order order) {
        if (order == null) {
            System.out.println("No active order.");
        return;
        }

    System.out.println("\n=========== RECEIPT ==========");
    System.out.println("Order ID: " + order.getOrderId());
    System.out.println("--------------------------------");
    System.out.println("Product\tQty\tPrice\tDiscount\tTotal");

    for (Order item : order.getItems()) {
        System.out.println(
            item.getProductName() + "\t" +
            item.getQuantity() + "\t" +
            item.getPrice() + "\t" +
            item.getDiscount() + "\t\t" +
            item.getTotal()
        );
    }

    System.out.println("|==============================|");
    System.out.println("Grand Total: $" + order.getGrandTotal());
    System.out.println("|==============================|");}

    public void viewReceiptById() {

        if (orders.isEmpty()) {
            System.out.println("No completed orders.");
            return;
        }

        System.out.print("Enter Order ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        for (Order order : orders) {
        if (order.getOrderId() == id) {
            printReceipt(order);
            return;
        }
    }

    System.out.println("Order not found.");
    }
    public void viewallrecipts(){
        if (orders.isEmpty()) {
            System.out.println("No completed orders yet.");
            return;
        }
        System.out.println("\n=========== ALL RECEIPTS ===========");
        for (Order order : orders) {
            printReceipt(order);
        }


    }




    // Login
    private IStaff login(){
        System.out.println("Please input your \"User Name\" \"Phone Number\" and \"Password\"");
        System.out.print("User Name : ");
        String name = sc.nextLine();
        System.out.print("Phone Number : ");
        String pnum = sc.nextLine();
        System.out.print("Password : ");
        String pwnum = sc.nextLine();
        for (IStaff staff : staffs){
            if(staff.getUsername().equals(name) && staff.getPhoneNumber().equals(pnum) && staff.checkPassword(pwnum)){
                return staff;
            }
        }
        return null;
    }
    public void manageStaffMenu() {
        int choice;
        do {
            System.out.println("\n=== Manage Staff Menu ===");
            System.out.println("1. Add Staff");
            System.out.println("2. View Staff List");
            System.out.println("3. Update Staff");
            System.out.println("4. Search Staff");
            System.out.println("5. Remove Staff");
            System.out.println("0. Back");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine(); 
            switch (choice) {
                case 1:
                    createStaff();;
                    break;
                case 2:
                    ShowStaffList();;
                    break;
                case 3:
                    updateStaff();
                    break;
                case 4:
                    SearchStaff();
                    break;
                case 5:
                    removeStaff();
                    break;
                case 0:
                    System.out.println("Returning to main menu...");
                    break;
                default:
                    System.out.println("Invalid choice....!");
            }
        } while (choice != 0);
    }

    private void updateStock(){
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
                    break;  
                case 2:
                    System.out.println("\n Increase Item's Quantity");
                    break;  
                case 3:    
                    System.out.println("\n Decrease Item's Quantity");
                    break; 
                case 4:
                    System.out.println("\n Delete Item");
                    break; 
                default:
                    System.out.println("Invalid option!");
            }
        } while (choice!=0);

    }


    void run(){
        IStaff staff = login();
        if (staff == null) {
            System.out.println("Login failed.");
            return;
        }else{
            System.out.println("Login successful. Welcome, " + staff.getUsername() + "!");
            int choice;
            if(staff.getPosition().equalsIgnoreCase("Manager")){
                System.out.println(" This is Manager");
                do{
                    System.out.println("Please select your action : ");
                    System.out.println("1. View Products");             
                    System.out.println("2. Update Stock");
                    System.out.println("3. View Receipt");
                    System.out.println("4. Manage account");                   
                    System.out.println("0. Logout");
                    System.out.print("Your choice : ");
                    choice = sc.nextInt();
                   
                    switch (choice) {
                        case 1:
                            if(staff.can(VIEW_PRODUCTS)){
                                printItems(products);
                            }else {
                                System.out.println("cannot do!! no permission");
                            }
                            break;
                        case 2:
                            if(staff.can(UPDATE_STOCK)){
                                System.out.print("This is Update Stock");
                                updateStock();
                            }else {
                                System.out.println("cannot do!! no permission");
                            }                           
                            break;
                        case 3:
                            if(staff.can(VIEW_RECIPT)){
                                System.out.println("This is View Receipt");
                                viewReceiptById();
                            }else {
                                System.out.println("cannot do!! no permission");
                            }                            
                            break;
                        case 4:
                            if(staff.can(MANAGE_ACCOUNT)){
                                System.out.println("This is Manage account");
                                manageStaffMenu();
                            }else {
                                System.out.println("cannot do!! no permission");
                            }
                            break;
                        case 0:
                            System.out.println("Logging out...");
                            return;
                        default:
                            System.out.println("Invalid choice. Please try again.");
                    }
                }while (choice!=0);
                    
            }else if(staff.getPosition().equalsIgnoreCase("Stocker")){
                System.out.println(" This is Stocker");
                do{
                    System.out.println("Please select your action : ");
                    System.out.println("1. View Products");             
                    System.out.println("2. Update Stock");
                    System.out.println("0. Logout");
                    System.out.print("Your choice : ");
                    choice = sc.nextInt();
                   
                    switch (choice) {
                        case 1:
                            if(staff.can(VIEW_PRODUCTS)){
                                printItems(products);
                            }else {
                                System.out.println("cannot do!! no permission");
                            }
                            break;
                        case 2:
                            if(staff.can(UPDATE_STOCK)){
                                System.out.print("This is Update Stock");
                                updateStock();
                            }else {
                                System.out.println("cannot do!! no permission");
                            }                         
                            break;
                        case 0:
                            System.out.println("Logging out...");
                            return;
                        default:
                            System.out.println("Invalid choice. Please try again.");
                    }
                }while (choice!=0);
            }else if(staff.getPosition().equalsIgnoreCase("Cashier")){
                System.out.println(" This is Cashier");
                do{
                    System.out.println("Please select your action : ");
                    System.out.println("1. Complete Order");             
                    System.out.println("2. Sell Product");
                    System.out.println("3. view Receipt By ID");
                    System.out.println("4. view all Receipt");
                    System.out.println("0. Logout");
                    System.out.print("Your choice : ");
                    choice = sc.nextInt();
                   
                    switch (choice) {
                        case 1:
                            if(staff.can(COMPLETE_ORDER)){
                                System.out.print("This is Complete Order");
                                completeOrder();
                            }else {
                                System.out.println("cannot do!! no permission");
                            }                        
                            break;
                        case 2:
                            if(staff.can(SELL_PRODUCT)){
                                System.out.print("This is Sell Product");
                                sellItem();
                            }else {
                                System.out.println("cannot do!! no permission");
                            }                           
                            break;
                        case 3:
                            if(staff.can(VIEW_RECIPT)){
                                System.out.println("This is View Receipt by ID");
                                viewReceiptById();
                            }else {
                                System.out.println("cannot do!! no permission");
                            }                          
                            break;
                        case 4:
                            if(staff.can(VIEW_RECIPT)){
                                System.out.println("This is view all Receipt");
                                viewallrecipts();
                            }else {
                                System.out.println("cannot do!! no permission");
                            }                           
                            break;
                        case 0:
                            System.out.println("Logging out...");
                            return;
                        default:
                            System.out.println("Invalid choice. Please try again.");
                    }

                }while (choice!=0);
            }
        }
    } 
}


