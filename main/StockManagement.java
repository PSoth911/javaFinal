package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import model.Order;
import model.Product;
import user.Cashier;
import user.IStaff;
import user.Manager;
import user.Staff;
import user.Stocker;

import java.io.Console;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class StockManagement {
    Scanner sc = new Scanner(System.in);
    DateTimeFormatter convert = DateTimeFormatter.ofPattern("d/M/yyyy");

    public static final String VIEW_PRODUCTS = "View Products";
    public static final String SELL_PRODUCT = "Sell Product";
    public static final String UPDATE_STOCK = "Update Stock";
    public static final String VIEW_RECIPT = "View Receipt";
    public static final String MANAGE_ACCOUNT = "Manage account";
    public static final String COMPLETE_ORDER = "Complete order";
    public static final String ADD_ITEM = "Add Item";
    public static final String DELETE_ITEM = "Delete Item"; 

    private String shopName;
    private String address;
    ArrayList<Product> products;
    ArrayList<Staff> staffs;
    ArrayList<Order> orders;
    private Order currentOrder = null;

    public StockManagement(String shopName, String address) {
        this.setShopName(shopName);
        this.setAddress(address);

        products = new ArrayList<>();
        staffs = new ArrayList<>();
        orders = new ArrayList<>();

        setDefaultData();
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

    @FunctionalInterface
    public interface StaffFilter {
        boolean test(Staff s);
    }

    public void showActiveStaff(ArrayList<Staff> staffs) {
        // lambda Expression
        StaffFilter filter = s -> s.isActive();

        for (Staff s : staffs) {
            if (filter.test(s)) {
                System.out.println(s);
            }
        }
    }

    // Product Management
    public void addItem(String category, String name, int quantity, double importPrice, double exportPrice,
            LocalDate expiredDate) {
        try {
            LocalDate importDate = LocalDate.now();
            if (expiredDate.isBefore(importDate)) {
                System.out.println("Expired date must be after import date. Please try again.");
                return;
            }
            products.add(new Product(category, name, quantity, importPrice, importDate, exportPrice, expiredDate));
        } catch (Exception e) {
            System.out.println("Invalid date format. Please use D/M/YYYY.");
        }
    }

    public void deleteItem(int id) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == id) {
                products.remove(i);
                break;
            }
        }
    }

    public void increaseItemByValue(int id, int value) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == id) {
                products.get(i).setQuantity(products.get(i).getQuantity() + value);
                break;
            }
        }
    }

    public void decreaseItemByValue(int id, int value) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == id) {
                if (products.get(i).getQuantity() >= value) {
                    products.get(i).setQuantity(products.get(i).getQuantity() - value);
                    break;
                } else if (products.get(i).getQuantity() == 0) {
                    System.out.println("Item is zero");
                } else {
                    System.out.println("Item's quantity < Decrease");
                }
            }
        }
    }

    public static void printItems(ArrayList<Product> items) {
        System.out.println("ID\tCategory\tName\tQuantity\tImport Price\tImport Date\tExport Price\t\tExpired Date");
        for (Product item : items) {
            System.out.println(item.getId() + "\t" + item.getCategory() + "\t\t" + item.getName() + "\t"
                    + item.getQuantity() + "\t\t" + item.getImportPrice() + "\t\t" + item.getImportDate() + "\t\t"
                    + item.getExportPrice() + "\t\t" + item.getExpiredDate());
        }
    }

    // Staff Management
    private void removeStaff(IStaff staff) {

        ShowStaffList();
        System.out.println("Enter THe StaffID to Update:");
        int id = sc.nextInt();
        sc.nextLine();
        Staff s = findStaffById(id);
        if (s == staff) {
            System.out.println("You cannot remove your own account!");
            return;
        }
        if (s != null) {
            System.out.println("Are you sure you want to remove " + s.getUsername()
                    + "? (Y/N)");
            String confirm = sc.nextLine();
            if (confirm.equalsIgnoreCase("Y")) {
                s.setActive(false);
                System.out.println("Staff removed successfully!");
            } else {
                System.out.println("Remove cancelled.");
            }
        } else {
            System.out.println("Staff is not FOund....!");
        }

    }

    private void updateStaff() {
        ShowStaffList();
        System.out.println("Enter THe StaffID to Update:");
        int id = sc.nextInt();
        sc.nextLine();
        Staff s = findStaffById(id);

        if (s != null) {
            System.out.print("New username: ");
            String username = sc.nextLine();

            System.out.print("Enter oldUsername");
            String oldusername = sc.nextLine();

            System.out.print("Enter password of this staff");
            String password = sc.nextLine();

            System.out.print("New phone number: ");
            String phone = sc.nextLine();

            System.out.print("New email: ");
            String email = sc.nextLine();

            if (!validstaffinput(username, password, phone, email)) {
                throw new IllegalArgumentException("Update failed due to invalid input.");
            }
            s.setUsername(username, oldusername, password);
            s.setPhoneNumber(phone, password);
            s.setEmail(email, password);
            System.out.print("Enter new password: ");
            String newPass = sc.nextLine();

            s.setpassword(password, newPass);
            System.out.println("staff updated successfully!");
        } else {
            System.out.println("staff not found!");
        }
    }

    private Staff findStaffById(int id) {
        for (int i = 0; i < staffs.size(); i++) {
            if (staffs.get(i).getId() == id) {
                return staffs.get(i);
            }
        }
        return null;
    }

    private void SearchStaff() {
        boolean check = false;
        System.out.println("Enter UserName staff That you Want to search:");
        String name = sc.nextLine();
        System.out.println("Enter Phone Number staff That you Want to search:");
        String phone = sc.nextLine();
        for (int i = 0; i < staffs.size(); i++) {
            if (staffs.get(i).getUsername().equalsIgnoreCase(name) && staffs.get(i).getPhoneNumber().equals(phone)) {
                System.out
                        .println("----------------------------------------------------------------------------------");
                System.out.println("ID\tUsername\t\tPhone\t\t\tEMAIL");
                System.out.println(staffs.get(i).getId() + "\t"
                        + staffs.get(i).getUsername() + "\t\t"
                        + staffs.get(i).getPhoneNumber() + "\t\t"
                        + staffs.get(i).getEmail());
                check = true;
            }
            if (check != true) {
                System.out.println("Staff not found...!");
            }
        }

    }

    private boolean validstaffinput(String username, String password, String phoneNumber, String email) {
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
        System.out.println("ID\tUsername\t\tPhone\t\t\tEMAIL\t\t\tActive");
        System.out.println("----------------------------------------------------------------------------------");
        for (int i = 0; i < staffs.size(); i++) {
            System.out.println(staffs.get(i).getId() + "\t"
                    + staffs.get(i).getUsername() + "\t\t"
                    + staffs.get(i).getPhoneNumber() + "\t\t"
                    + staffs.get(i).getEmail() + "\t\t"
                    + staffs.get(i).isActive());
        }
    }

    public String PostionChoice() {
        int choice;
        System.out.println("1. Manager");
        System.out.println("2. Cashier");
        System.out.println("3. Stocker");
        System.out.print("Enter position that you want to create ");
        choice = sc.nextInt();
        if (choice == 1) {
            return "Manager";
        } else if (choice == 2) {
            return "Cashier";
        } else if (choice == 3) {
            return "Stocker";
        } else {
            return "UNKNOW";
        }
    }

    public void createStaff() {
        System.out.print("Enter Username: ");
        String username = sc.nextLine();
        System.out.print("Enter Password: ");
        String password = sc.nextLine();
        System.out.print("Enter Phone Number: ");
        String phoneNumber = sc.nextLine();
        System.out.print("Enter Email: ");
        String email = sc.nextLine();
        System.out.print("Enter Salary");
        float salary = sc.nextFloat();
        String position = PostionChoice();

        if (!validstaffinput(username, password, phoneNumber, email)) {
            throw new IllegalArgumentException("Staff NOT added because invalid input.");

        }
        for (int i = 0; i < staffs.size(); i++) {
            if (username.equalsIgnoreCase(staffs.get(i).getUsername())) {
                System.out.println("This staff already exits...!");
                return;
            }
        }
        if (position.equalsIgnoreCase("Manager")) {
            System.out.print("Enter Bonus: ");
            float bonus = sc.nextFloat();
            staffs.add(new Manager(username, password, phoneNumber, email, LocalDate.now().toString(), salary, bonus));
            System.out.println("New Manager Add Sucessfully...!");
        } else if (position.equalsIgnoreCase("Cashier")) {
            staffs.add(new Cashier(username, password, phoneNumber, email, LocalDate.now().toString(), salary));
            System.out.println("New Cashier Add Sucessfully...!");
        } else if (position.equalsIgnoreCase("Stocker")) {
            System.out.print("Enter Shift (Morning/Evening/Night): ");
            String shift = sc.next();
            staffs.add(new Stocker(username, password, phoneNumber, email, LocalDate.now().toString(), salary, shift));
            System.out.println("New Stocker Add Sucessfully...!");
        } else {
            System.out.println("Invalid input.");
        }

    }

    // Order Management

    public void sellItem() {
        System.out.print("Enter item name to sell: ");
        String name = sc.next();
        boolean found = false;
        for (Product item : products) {
            if (item.getName().equalsIgnoreCase(name)) {
                try {
                    found = true;
                    System.out.print("Enter quantity to sell: ");
                    int qty = sc.nextInt();

                    if (qty <= item.getQuantity()) {
                        item.setQuantity(item.getQuantity() - qty);
                        double afterDiscount = item.getExportPrice() - (item.getDiscount() * item.getExportPrice());
                        double totalPrice = Math.round(qty * afterDiscount * 100.0) / 100.0;
                        System.out.println("Discount : " + item.getDiscount() * 100 + "%");
                        System.out.println("Sold " + qty + " " + item.getName() + "(s). Total: $" + totalPrice);
                        Order orderitem = new Order(item.getName(), qty, item.getExportPrice(), item.getDiscount());
                        if (currentOrder == null) {
                            currentOrder = new Order();
                        }
                        currentOrder.addItem(orderitem);
                        System.out.println("Item added to current order.");
                    } else {
                        throw new IllegalArgumentException("Not enough stock! Current stock: " + item.getQuantity());
                    }
                    break;
                } catch (Exception e) {
                    System.out.println("Invalid input (NaN). Please try again.(message from sell item)");
                    sc.nextLine(); // Clear the invalid input
                }

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
                            item.getTotal());
        }

        System.out.println("|==============================|");
        System.out.println("Grand Total: $" + order.getGrandTotal());
        System.out.println("|==============================|");
    }

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

    public void viewallrecipts() {
        if (orders.isEmpty()) {
            System.out.println("No completed orders yet.");
            return;
        }
        System.out.println("\n=========== ALL RECEIPTS ===========");
        for (Order order : orders) {
            printReceipt(order);
        }

    }

    public void viewRecipt() {
        System.out.println("Do you want to view all receipts or a specific one?");
        System.out.println("1. View All Receipts");
        System.out.println("2. View Specific Receipt");
        System.out.println("0. Back");
        System.out.print("Enter your choice: ");
        int choice = sc.nextInt();
        sc.nextLine();
        if (choice == 1) {
            viewallrecipts();
        } else if (choice == 2) {
            viewReceiptById();
        } else if (choice == 0) {
            System.out.println("Returning to main menu...");
        } else {
            System.out.println("Invalid choice.");
        }
    }

    // Login
    private IStaff login() {
        System.out.println("==================Login==================");
        System.out.println("Welcome to " + shopName + " Stock Management System");
        System.out.print("Enter User Name : ");
        String name = sc.nextLine();
        System.out.print("Enter Phone Number : ");
        String pnum = sc.nextLine();
        Console console = System.console();
        String pwnum;
        if (console != null) {
            char[] passwordChars = console.readPassword("Password : ");
            pwnum = new String(passwordChars);
        } else {
            // Fallback if Console is not available (e.g., in IDEs)
            System.out.print("Enter Password : ");
            pwnum = sc.nextLine();
        }

        for (IStaff staff : staffs) {
            if (staff.getUsername().equals(name)) {

                if (!staff.getPhoneNumber().equals(pnum)) {
                    System.out.println("input incorrect or staff not found.");
                    return null;
                }

                if (!staff.checkPassword(pwnum)) {
                    System.out.println("Input incorrect or staff not found.");
                    return null;
                }

                return staff; // success
            }else {
                System.out.println("Input incorrect or staff not found.");
                return null;
            }
        }
        return null;
    }

    public void manageStaffMenu(IStaff staff) {
        int choice;
        do {
            try {
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
                        try {
                            createStaff();
                        } catch (IllegalArgumentException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 2:
                        ShowStaffList();
                        ;
                        break;
                    case 3:
                        try {
                            updateStaff();
                        } catch (IllegalArgumentException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 4:
                        SearchStaff();
                        break;
                    case 5:
                        removeStaff(staff);
                        break;
                    case 0:
                        System.out.println("Returning to main menu...");
                        break;
                    default:
                        System.out.println("Invalid choice....!");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please try again with a number.(message from manage staff menu)");
                sc.nextLine(); // Clear the buffer
                choice = -1; // Reset choice to avoid accidental logout or exit
            }
        } while (choice != 0);
    }

    private String setCategory() {
        int choice;
        System.out.println("1. Food");
        System.out.println("2. Drink");
        System.out.println("3. Household");
        System.out.println("4. Personal Care");
        System.out.println("5. Snacks");
        System.out.println("6. Frozen Food");
        System.out.print("Enter category that you want to create from 1 to 6 : ");
        choice = sc.nextInt();
        sc.nextLine(); // Clear the buffer

        switch (choice) {
            case 1:
                return "Food";
            case 2:
                return "Drink";
            case 3:
                return "Household";
            case 4:
                return "Personal Care";
            case 5:
                return "Snacks";
            case 6:
                return "Frozen Food";
            default:
                System.out.println("Invalid choice. Default category set to 'Food'.");
                return null;
        }
    }

    private void updateStock() {
        int choice;
        do {
            try {
                System.out.println(">>>");
                System.out.println("1. Increase Item's Quantity");
                System.out.println("2. Decrease Item's Quantity");
                System.out.println("0. Back");
                System.out.print("Enter option: ");
                choice = sc.nextInt();
                switch (choice) {
                    case 1:
                        try {
                            printItems(products);
                            System.out.println("\n Increase Item's Quantity");
                            System.out.println("Please Input the ID and Increase value");
                            int id;
                            int inc;
                            System.out.print("ID >>");
                            id = sc.nextInt();
                            System.out.print("Increase value >>");
                            inc = sc.nextInt();
                            increaseItemByValue(inc, id);
                            break;
                        } catch (Exception e) {
                            System.out.println("Invalid input. Please try again.(message from increase item)");
                            sc.nextLine(); // Clear the buffer
                            break;
                        }
                    case 2:
                        try {
                            printItems(products);
                            System.out.println("\n Decrease Item's Quantity");
                            System.out.println("Please Input the ID and Decrease value");
                            int id2;
                            int dec;
                            System.out.print("ID >>");
                            id2 = sc.nextInt();
                            System.out.print("Decrease value >>");
                            dec = sc.nextInt();
                            decreaseItemByValue(id2, dec);
                            break;
                        } catch (Exception e) {
                            System.out.println("Invalid input. Please try again.(message from decrease item)");
                            sc.nextLine(); // Clear the buffer
                            break;
                        }
                    case 0:
                        System.out.println("Returning to main menu...");
                        break;
                    default:
                        System.out.println("Invalid option!");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please try again.(message from update stock)");
                sc.nextLine(); // Clear the buffer
                choice = -1; // Reset choice to avoid accidental logout or exit
            }
        } while (choice != 0);

    }

    private void additem() {
        System.out.println("\n Add New Item");
        System.out.println("Please input product information");

        String category;
        String name;
        int qty;
        double importPrice;
        double exportPrice;
        LocalDate expireDate = null;

        while (true) {
            try {
                boolean validCategory = false;
                boolean validQty = false;
                boolean validImportPrice = false;
                boolean validExportPrice = false;
                boolean validExpireDate = false;

                do {
                    System.out.println("Category >> ");
                    category = setCategory();
                    if (category != null) {
                        validCategory = true;
                    } else {
                        System.out.println("Invalid category. Please try again.");
                    }
                } while (!validCategory);

                System.out.print("Name >> ");
                name = sc.next();

                do {
                    System.out.print("Quantity >> ");
                    qty = sc.nextInt();
                    if (qty > 0) {
                        validQty = true;
                    } else {
                        System.out.println("Invalid quantity. Please enter a positive integer.");
                    }
                } while (!validQty);

                do {
                    System.out.print("Import Price $ >> ");
                    importPrice = sc.nextDouble();
                    if (importPrice <= 0) {
                        System.out.println("Invalid Import Price. Please try again.");
                    } else {
                        validImportPrice = true;
                    }
                } while (!validImportPrice);

                do {
                    System.out.print("Export Price $ >> ");
                    exportPrice = sc.nextDouble();
                    if (exportPrice <= 0) {
                        System.out.println("Invalid Export Price. Please try again.");
                        continue;
                    }
                    validExportPrice = true;
                } while (!validExportPrice);

                do {
                    System.out.print("Expire Date as (D/M/YYYY) >> ");
                    String expireDateStr = sc.next();
                    try {
                        expireDate = LocalDate.parse(expireDateStr, convert);
                        validExpireDate = true;
                    } catch (DateTimeParseException e) {
                        System.out.println("Invalid date format. Please try again.");
                    }
                } while (!validExpireDate);

                break; // Exit the loop if all inputs are valid
            } catch (Exception e) {
                System.out.println("Invalid input. Please try again.(message from add item)");
                sc.nextLine(); // Clear the buffer
            }
        }

        addItem(category, name, qty, importPrice, exportPrice, expireDate);
        printItems(products);
    }

    private void deleteitem() {
        try {
            printItems(products);
            System.out.println("\n Delete Item");
            System.out.println("Please Input the ID of product");
            int id3;
            System.out.print("ID >>");
            id3 = sc.nextInt();
            deleteItem(id3);
            System.out.println("Item deleted successfully!");
            printItems(products);
        } catch (Exception e) {
            System.out.println("Invalid input. Please try again.(message from delete item)");
            sc.nextLine(); // Clear the buffer
        }
    }

    private void setDefaultData() {
        // Anonymous Inner Class
        Staff admin = new Manager(
                "admin",
                "admin123",
                "0123456789",
                "admin@gmail.com",
                LocalDate.now().toString(),
                1000,
                200) {
            @Override
            public boolean can(String action) {
                return true;
            }
        };
        staffs.add(admin);

        // Normal
        Staff stocker1 = new Stocker("stocker1", "stock123", "0987654321", "stocker@gmail.com",
                LocalDate.now().toString(), 500, "Morning");
        staffs.add(stocker1);
        Staff cashier1 = new Cashier("cashier1", "cash1234", "0112233445", "cashier@gmail.com",
                LocalDate.now().toString(), 400);
        staffs.add(cashier1);

        products.add(new Product("Food", "Bread", 100, 0.5, LocalDate.now(), 1.0, LocalDate.now().plusDays(7)));
        products.add(new Product("Drink", "Water", 200, 0.2, LocalDate.now(), 0.5, LocalDate.now().plusDays(30)));
        products.add(new Product("Food", "Cake", 50, 1.0, LocalDate.now(), 2.0, LocalDate.now().plusDays(3)));
        products.add(new Product("Food", "Milk", 80, 0.8, LocalDate.now(), 1.5, LocalDate.now().plusDays(5)));
        products.add(new Product("Drink", "Juice", 120, 0.3, LocalDate.now(), 0.8, LocalDate.now().plusDays(20)));
    }

    void run() {
        int choice = 1;
        do {
            IStaff staff = login();
            if (staff == null) {
                System.out.println("Login failed.");
                continue;
            }

            System.out.println("Login successful. Welcome, " + staff.getUsername() + "!");
            boolean isLogin = true;
            do {
                try {
                    int options = 1;
                    Map<Integer, String> actionMap = new HashMap<>();
                    if (staff.can(VIEW_PRODUCTS)) {
                        System.out.println(options + ". View Products");
                        actionMap.put(options++, VIEW_PRODUCTS);
                    }
                    if (staff.can(UPDATE_STOCK)) {
                        System.out.println(options + ". Update Stock");
                        actionMap.put(options++, UPDATE_STOCK);
                    }
                    if (staff.can(MANAGE_ACCOUNT)) {
                        System.out.println(options + ". Manage account");
                        actionMap.put(options++, MANAGE_ACCOUNT);
                    }
                    if (staff.can(COMPLETE_ORDER)) {
                        System.out.println(options + ". Complete Order");
                        actionMap.put(options++, COMPLETE_ORDER);
                    }
                    if (staff.can(SELL_PRODUCT)) {
                        System.out.println(options + ". Sell Product");
                        actionMap.put(options++, SELL_PRODUCT);
                    }
                    if (staff.can(VIEW_RECIPT)) {
                        System.out.println(options + ". View Receipt");
                        actionMap.put(options++, VIEW_RECIPT);
                    }
                    if (staff.can(ADD_ITEM)) {
                        System.out.println(options + ". Add Item");
                        actionMap.put(options++, ADD_ITEM);
                    }
                    if (staff.can(DELETE_ITEM)) {
                        System.out.println(options + ". Delete Item");
                        actionMap.put(options++, DELETE_ITEM);
                    }
                    System.out.println(options + ". Logout");
                    int logoutOption = options;
                    System.out.println("0. Exit");

                    System.out.print("Your choice : ");
                    choice = sc.nextInt();
                    sc.nextLine();

                    if (choice == 0) {
                        System.out.println("Exit");
                        return;
                    } else if (choice == logoutOption) {
                        System.out.println("Logging out...");
                        isLogin = false;
                    } else if (actionMap.containsKey(choice)) {
                        String action = actionMap.get(choice);
                        switch (action) {
                            case VIEW_PRODUCTS:
                                printItems(products);
                                break;
                            case UPDATE_STOCK:
                                updateStock();
                                break;
                            case MANAGE_ACCOUNT:
                                manageStaffMenu(staff);
                                break;
                            case COMPLETE_ORDER:
                                completeOrder();
                                break;
                            case SELL_PRODUCT:
                                try {
                                    sellItem();
                                } catch (IllegalArgumentException e) {
                                    System.out.println(e.getMessage());
                                }
                                break;
                            case VIEW_RECIPT:
                                viewRecipt();
                                break;
                            case ADD_ITEM:
                                additem();  
                                break;
                            case DELETE_ITEM:   
                                deleteitem();
                                break;
                            default:
                                System.out.println("Invalid choice. Please try again.");
                        }
                    } else {
                        System.out.println("Invalid choice. Please try again.");
                    }
                } catch (Exception e) {
                    System.out.println("Invalid input. Please try again with a number.(message from main menu)");
                    sc.nextLine(); // Clear the buffer
                    choice = -1; // Reset choice to avoid accidental logout or exit
                }
            } while (choice != 0 && isLogin);

        } while (choice != 0);
    }

    void test() {
        int choice;
        do {
            System.out.println("1. View Products");
            System.out.println("2. Update Stock");
            System.out.println("3. Manage account");
            System.out.println("4. Complete Order");
            System.out.println("5. Sell Product");
            System.out.println("6. View Receipt");
            System.out.println("0. Exit");

            System.out.print("Your choice : ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    printItems(products);
                    break;
                case 2:
                    updateStock();
                    break;
                case 3:
                    manageStaffMenu(staffs.get(0));
                    break;
                case 4:
                    completeOrder();
                    break;
                case 5:
                    try {
                        sellItem();
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 6:
                    viewRecipt();
                    break;
                case 0:
                    System.out.println("Exit");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);
    }

}
