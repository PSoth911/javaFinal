package user;
public abstract class Staff implements IStaff {
    private int id;        
    private static int count=0;      
    private String username;     
    private String password;     
    private String phoneNumber;
    private String email;  
    private String dateHired;     

  
    public abstract boolean can(String action);

    public Staff(String username, String password, String phoneNumber,String email, String dateHired){ 
        this.setId();
        this.setUsername(username);
        this.setpassword(password);
        this.setPhoneNumber(phoneNumber);
        this.setEmail(email);
        this.setHiredDate(dateHired);
    }

    // // New constructor for promotion
    // protected Staff(String username, String password, String phoneNumber, String email, String dateHired, int existingId) {
    //     this.id = existingId;  // <- Use the old id, do NOT increment count
    //     this.setUsername(username);
    //     this.setpassword(password);
    //     this.setPhoneNumber(phoneNumber);
    //     this.setEmail(email);
    //     this.setHiredDate(dateHired);
    // }

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
    protected String getPassword(){
        return password;
    }

    private void setId(){
         this.id = ++count;
    }

    private void setHiredDate(String dateHired){
        this.dateHired = dateHired;
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

    @Override
    public String toString() {
    return "Staff{" +"id=" + id +", username='" + username + '\'' +", phoneNumber='" + phoneNumber + '\'' +
            ", email='" + email + '\'' +", dateHired='" + dateHired + '\'' +'}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {return true;}
        if (obj == null || getClass() != obj.getClass()) {return false;}
        Staff s = (Staff) obj;
        return this.phoneNumber.equals(s.phoneNumber);
        }
    }
