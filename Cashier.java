public class Cashier implements IsStaff {
    private int id;        
    private static int count=1;      
    private String username;     
    private String password;     
    private String phoneNumber;
    private String email;  
    private String dateHired;     
    private String position;

    Cashier(String username, String password, String phoneNumber,String email, String dateHired, String position){ 
        this.id=count++;
        this.setUsername(username);
        this.setpassword(password);
        this.setPhoneNumber(phoneNumber);
        this.setEmail(email);
        this.dateHired = dateHired;
        this.setPosition(position);
    }
    public int getId(){
        return id;
    }
    public String getUsername() {
        return username;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getDateHired() {
        return dateHired;
    }

    public String getPosition() {
        return position;
    }

    private void setEmail(String email){
        if (email.matches("^[A-Za-z0-9._%+-]+@gmail\\.com$")) {
            this.email = email;
        }
    }
    // password must has 8 char include letter and number
    private void setpassword(String password){
        if(password.matches("^(?=.*[A-Za-z])(?=.*\\d).{8,}$"))
            this.password=password;

    }

    private void setUsername(String username) {
        if (username.matches("^[A-Za-z0-9]{5,}$")) {
            this.username = username;
        }
    }
    private void setPhoneNumber(String phoneNumber) {
        if (phoneNumber.matches("^\\d{8,}$")) {
            this.phoneNumber=phoneNumber;
        }
    }
    private void setPosition(String position) {
        this.position = position;
    }
    //user name at last 5 char include letter and numbers only
    public void setUsername(String username,String oldusername,String password) {
        if (username.matches("^[A-Za-z0-9]{5,}$")&& oldusername.equals(this.username)&& checkPassword(password)) {
            this.username = username;
        }else{
            System.out.println("fail..!");
        }
    }

    //real email include @,gmail.com
    public void setEmail(String email ,String password){
        if (email.matches("^[A-Za-z0-9._%+-]+@gmail\\.com$")&&checkPassword(password)) {
            this.email = email;
        }else{
            System.out.println("fail..!");
        }
    }
    public boolean checkPassword(String inputPassword) {
        return password.equals(inputPassword);
    }
    public void setpassword(String oldpassword,String newpassword){
        if(password.equals(oldpassword)){
            this.password=newpassword;
        }else{
            System.out.println("Wrong Oldpassword..!");
        }
    }
    //phoneNumebr has at least 8 digits
    public void setPhoneNumber(String phoneNumber,String password) {
        if (phoneNumber.matches("^\\d{8,}$")&& checkPassword(password)) {
            this.phoneNumber=phoneNumber;
        }else{
            System.out.println("fail....!");
        }
    }
}
