
import java.util.Scanner;


public class Main{
    Scanner sc = new Scanner(System.in);
    InsertData data = new InsertData();
    void demoAdd(){
        data.addItem("Fruit", "Apple", 10, 2.5, 20240101, 3.5, 20240201);

        data.addItem("Drink", "Milk", 20, 1.2, 20240105, 2.0, 20240120);

        data.addItem("Snack", "Biscuit", 50, 0.6, 20240110, 1.2, 20240610);
    }
    void run(){
        demoAdd();
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
                Admin admin = new Admin(data);
                admin.start();
            }else if(choice==2){
                Staff staff = new Staff(data);
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