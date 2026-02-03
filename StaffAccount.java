public class StaffAccount {
    int id;        
    static int count=1;      
    String username;     
    String password;     
    String phoneNumber;
    String email;  
    String dateHired;     

    StaffAccount(String username, String password, String phoneNumber,String email, String dateHired) {
        this.id=count++;
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.email=email;
        this.dateHired = dateHired;
    }
}
