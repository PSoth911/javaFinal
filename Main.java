
import java.util.Scanner;


public class Main{
    Scanner sc = new Scanner(System.in);
    Managestaff manage = new Managestaff();
    ManageProduct data = new ManageProduct();
    AdminList adminList = new AdminList();

    void demoAdd(){
        data.addItem("Fruit", "Apple", 10, 2.5, "01/02/2022", 3.5, "01/02/2022");
        data.addItem("Drink", "Milk", 20, 1.2, "01/02/2022", 2.0, "01/02/2022");
        data.addItem("Snack", "Biscuit", 50, 0.6, "01/02/2022", 1.2, "01/02/2022");
    }

    void demoAddAdmin(){
        adminList.addAdminAcc("admin","123","123","123");
    }

    void demoAddStaff(){
        manage.addStaff("aaa","123","123","123@gmail.com");
        manage.addStaff("bbb","456","456","456@gmail.com");
        manage.addStaff("ccc","789","789","789@gmail.com");
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
    public void main(String[] args){ 
        run();
    }
}