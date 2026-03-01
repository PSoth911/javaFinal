public interface IStaff {
   String getUsername();
   String getPhoneNumber();
   int getId();
   String getEmail();
   String getPosition();
   boolean checkPassword(String password);
   void setpassword(String oldpass, String newPass);
   void setUsername(String username,String oldusername,String pass);
   void setPhoneNumber(String phonenumber,String password);
   void setEmail(String email,String password);

   boolean can(String action);
}
