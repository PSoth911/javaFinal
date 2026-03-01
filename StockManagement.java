
import java.util.ArrayList;
import java.util.Scanner;
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

    public StockManagement(String shopName, String address) {
        this.setShopName(shopName);
        this.setAddress(address);

        products = new ArrayList<>();
        staffs = new ArrayList<>();
        orders = new ArrayList<>();

        
        setDefaultData();
    }


    private void setDefaultData(){
        Manager defaultAdmin = new Manager("admin123", "Admin@123", "0123456789","admin@gmail.com");
        staffs.add(defaultAdmin);

        Cashier defaultCashier = new Cashier("cashier123", "Cashier@123", "0987654321","cashier@gmail.com", LocalDate.now().toString());
        staffs.add(defaultCashier);

        Stocker defaultStocker = new Stocker("stocker123", "Stocker@123", "0112233445","stocker@gmail.com", LocalDate.now().toString());
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
    

    // Order Management



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
            if(staff.getUsername().equals(name) && staff.getPhoneNumber().equals(pnum) && staff.getPassword().equals(pwnum)){
                return staff;
            }
        }
        return null;
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
                            printItems(products);
                            break;
                        case 2:
                            System.out.print("This is Update Stock");
                            break;
                        case 3:
                            System.out.println("This is View Receipt");
                            break;
                        case 4:
                            System.out.println("This is Manage account");
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
                            printItems(products);
                            break;
                        case 2:
                            System.out.print("This is Update Stock");
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
                    System.out.println("3. View Receipt");
                    System.out.println("0. Logout");
                    System.out.print("Your choice : ");
                    choice = sc.nextInt();
                   
                    switch (choice) {
                        case 1:
                            System.out.print("This is Complete Order");
                            break;
                        case 2:
                            System.out.print("This is Sell Product");
                            break;
                        case 3:
                            System.out.println("This is View Receipt");
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


