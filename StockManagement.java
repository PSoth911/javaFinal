
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;

public class StockManagement {
    Scanner sc = new Scanner(System.in);
    Managestaff manage = new Managestaff();
    ManageProduct data = new ManageProduct();
    ArrayList<AdminAccount> adminList = new ArrayList<>();

    void addAdminAcc(String username, String password, String phoneNumber,String email){
        adminList.add(new AdminAccount(username,password,phoneNumber,email));
    }

    void demoAdd(){
        data.addItem("Fruit", "Apple", 10, 2.5, "01/02/2022", 3.5, "18/02/2026");
        data.addItem("Drink", "Milk", 20, 1.2, "01/02/2022", 2.0, "19/02/2026");
        data.addItem("Snack", "Biscuit", 50, 0.6, "01/02/2022", 1.2, "20/02/2026");
    }

    void demoAddAdmin(){
        addAdminAcc("Admin1", "Pass1234", "0123456789", "admin1@gmail.com");
    }

    void demoAddStaff(){
        manage.addStaff("RathhDaro","Pass1234","038273644","123@gmail.com","Manager");
        manage.addStaff("Meassokpisey","Abcdef12","083293728","456@gmail.com","Staff");
        manage.addStaff("Chanvatanaka","XyZ98765","028327327","789@gmail.com","Staff");
    }

    void run(){
        demoAdd();
        demoAddAdmin();
        demoAddStaff();
        int choice;
        do {
            System.out.println("=============Welcome To Stock Management System==============");
            System.out.println("    1.Admin");
            System.out.println("    2.Staff");
            System.out.println("    0.Exit");
            System.out.println("==============================================================");
            System.out.print("Please Enter an option To continue (0-2): ");
            choice = sc.nextInt();
            if (choice==1){
                Admin admin = new Admin(data,manage,adminList);
                admin.start();
            }else if(choice==2){
                Staff staff = new Staff(manage,data);
                staff.start();
            }else{
                System.out.println("Exit");
                return;
            }

        } while (choice!=0);

    }

    
}
