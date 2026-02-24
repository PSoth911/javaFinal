
public class AdminAccount {     
    String username;     
    private String password;     
    private String phoneNumber;
    private String email;    

    public AdminAccount(String username, String password, String phoneNumber,String email) {
        this.setUsername(username);
        this.setpassword(password);
        this.setPhoneNumber(phoneNumber);
        this.setEmail(email);
    }

    public String getUsername() {
        return username;
    }
    public String getEmail() {
        return email;
    }
    

    public void setUsername(String username) {
        if (username.matches("^[A-Za-z0-9]{5,}$")) {
            this.username = username;
        }
    }
    public boolean checkPassword(String inputPassword) {
        return password.equals(inputPassword);
    }

    public boolean checkPhonenumber(String inputPhoneNumber) {
        return phoneNumber.equals(inputPhoneNumber);
    }

    public void resetPassword(String oldpassword,String newpassword){
        if(password.equals(oldpassword)){
            this.password=newpassword;
        }else{
            System.out.println("Wrong Oldpassword..!");
        }
    }

    private void setpassword(String password){
        if(password.matches("^(?=.*[A-Za-z])(?=.*\\d).{8,}$"))
            this.password=password;

    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
}
