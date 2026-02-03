public class StaffAccount {
    int id;              
    String username;     
    String password;     
    String phoneNumber;
    String email;  
    String dateHired;     

    //register
    StaffAccount(String username, String password, String phoneNumber,String email, String dateHired) {
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.email=email;
        this.dateHired = dateHired;
    }

    //Login
    StaffAccount(String username,String passsword){
        this.username=username;
        this.password=passsword;
    }
}
