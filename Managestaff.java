
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
public class Managestaff {
    ArrayList<StaffAccount> Stafflist= new  ArrayList<>();
    Scanner sc = new Scanner(System.in);
    int count=1;
    void manageStaffMenu() {
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
                    addStaff();
                    break;
                case 2:
                    showStaffList();
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
    public void addStaff (){
        System.out.print("Enter Username: ");
        String username = sc.nextLine();
        System.out.print("Enter Password: ");
        String password = sc.nextLine();
        System.out.print("Enter Phone Number: ");
        String phoneNumber = sc.nextLine();
        System.out.print("Enter Email: ");
        String email= sc.nextLine();

        
        StaffAccount s = new StaffAccount(username, password, phoneNumber,email,LocalDate.now().toString());
        s.id=count++;
        Stafflist.add(s);
        System.out.println("New Staff Add Sucessfully...!"); 
    }

    void showStaffList() {
        System.out.println("--------------------------Staff Dashbaord------------------------");
        System.out.println("ID\tUsername\tPhone\t\tEMAIL\t\tDate Hired");
        System.out.println("-----------------------------------------------------------------");
        for (StaffAccount s : Stafflist) {
            System.out.println(s.id + "\t" 
                               + s.username + "\t\t" 
                               + s.phoneNumber + "\t\t" 
                               +s.email+"\t\t"
                               + s.dateHired);
        }
    }
    StaffAccount findStaffbyID(int id){
        for( StaffAccount s:Stafflist){
            if(s.id==id){
                return s;
            }
        }
        return null;
    }

    void SearchStaff(){
        System.out.println("Enter UserName Staff That you Want to search:");
        String name=sc.nextLine();
        System.out.println("Enter Phone Number Staff That you Want to search:");
        String phone=sc.nextLine();
        for(StaffAccount s:Stafflist){
            if(s.username.equals(name)&&s.phoneNumber.equals(phone)){
                System.out.println("-----------------------------------------------------------------");
                System.out.println("ID\tUsername\tPhone\t\tEMAIL\t\tDate Hired");
                System.out.println(s.id + "\t" 
                               + s.username + "\t\t" 
                               + s.phoneNumber + "\t\t" 
                               +s.email+"\t\t"
                               + s.dateHired);
            }else{
                System.out.println("Staff not found...!");
            }
        }
        
    }
    void updateStaff(){
        showStaffList();
        System.out.println("Enter THe StaffID to Update:");
        int id=sc.nextInt();
        sc.nextLine();
        StaffAccount staff = findStaffbyID(id);

        if (staff != null) {
            System.out.print("Enter new Username: ");
            staff.username=sc.nextLine();
            System.out.print("Enter new Password: ");
            staff.password=sc.nextLine();
            System.out.print("Enter new phone number: ");
            staff.phoneNumber=sc.nextLine();
            System.out.println("Enter New Email:");
            staff.email=sc.nextLine();
            System.out.println("Staff updated....!");
        } else {
            System.out.println("Staff not found!");
        }
    }

    void removeStaff(){
        showStaffList();
        System.out.println("Enter THe StaffID to Update:");
        int id=sc.nextInt();
        sc.nextLine();
        StaffAccount staff = findStaffbyID(id);
         if (staff != null) {
            System.out.println("Are you sure you want to remove " + staff.username + "? (Y/N)");
            String confirm = sc.nextLine();
                if (confirm.equalsIgnoreCase("Y")) {
                    Stafflist.remove(staff);
                    System.out.println("Staff removed successfully!");
                } else {
                    System.out.println("Remove cancelled.");
                }
         }else{
            System.out.println("Staff is not FOund....!");
         }

        
    }
    public ArrayList<StaffAccount> getStaffList() {
    return Stafflist;  // This exposes the list so Staff can use it
}

    


}
