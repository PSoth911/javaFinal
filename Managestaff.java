
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
    public void addStaff (){
        System.out.print("Enter Username: ");
        String username = sc.nextLine();
        System.out.print("Enter Password: ");
        String password = sc.nextLine();
        System.out.print("Enter Phone Number: ");
        String phoneNumber = sc.nextLine();
        System.out.print("Enter Email: ");
        String email= sc.nextLine();
        if (!validstaffinput(username, password, phoneNumber, email)) {
            System.out.println("Staff NOT added.");
            return;
        }
        StaffAccount s = new StaffAccount(username, password, phoneNumber,email,LocalDate.now().toString());
        Stafflist.add(s);
        System.out.println("New Staff Add Sucessfully...!"); 
    }

    public void addStaff (String username,String password,String phoneNumber,String email){   
        Stafflist.add(new StaffAccount(username, password, phoneNumber,email,LocalDate.now().toString()));
    }

    private void showStaffList() {
        System.out.println("------------------------------------Staff Dashbaord-------------------------------");
        System.out.println("ID\tUsername\t\tPhone\t\t\tEMAIL\t\t\tDate Hired");
        System.out.println("----------------------------------------------------------------------------------");
        for (StaffAccount s : Stafflist) {
            System.out.println(s.getId() + "\t" 
                               + s.getUsername() + "\t\t" 
                               + s.getPhoneNumber() + "\t\t" 
                               +s.getEmail()+"\t\t"
                               + s.getDateHired());
        }
    }
    private StaffAccount findStaffbyID(int id){
        for( StaffAccount s:Stafflist){
            if(s.getId()==id){
                return s;
            }
        }
        return null;
    }

    private void SearchStaff(){
        boolean check=false;
        System.out.println("Enter UserName Staff That you Want to search:");
        String name=sc.nextLine();
        System.out.println("Enter Phone Number Staff That you Want to search:");
        String phone=sc.nextLine();
        for(StaffAccount s:Stafflist){
            if(s.getUsername().equals(name)&&s.getPhoneNumber().equals(phone)){
                System.out.println("----------------------------------------------------------------------------------");
                System.out.println("ID\tUsername\t\tPhone\t\t\tEMAIL\t\t\tDate Hired");
                System.out.println(s.getId() + "\t" 
                               + s.getUsername() + "\t\t" 
                               + s.getPhoneNumber() + "\t\t" 
                               +s.getEmail()+"\t\t"
                               + s.getDateHired());
                               check=true;
            }
            if(check!=true){
                System.out.println("Staff not found...!");
            }
        }
        
    }
    private void updateStaff(){
        showStaffList();
        System.out.println("Enter THe StaffID to Update:");
        int id=sc.nextInt();
        sc.nextLine();
        StaffAccount staff = findStaffbyID(id);

        if (staff != null) {
            System.out.print("New username: ");
            String username = sc.nextLine();

            System.out.print("New phone number: ");
            String phone = sc.nextLine();

            System.out.print("New email: ");
            String email = sc.nextLine();
            if (!validstaffinput(username, "Temp1234", phone, email)) {
                System.out.println("Update failed due to invalid input.");
                return;
             }

            staff.setUsername(username);
            staff.setPhoneNumber(phone);
            staff.setEmail(email);
            
            System.out.print("Enter old password: ");
            String oldPass = sc.nextLine();

            System.out.print("Enter new password: ");
            String newPass = sc.nextLine();

            staff.setpassword(oldPass, newPass);
            System.out.println("Staff updated successfully!");
        } else {
        System.out.println("Staff not found!");
        }
    }

    private void removeStaff(){
        showStaffList();
        System.out.println("Enter THe StaffID to Update:");
        int id=sc.nextInt();
        sc.nextLine();
        StaffAccount staff = findStaffbyID(id);
         if (staff != null) {
            System.out.println("Are you sure you want to remove " + staff.getUsername()
             + "? (Y/N)");
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

    


}
