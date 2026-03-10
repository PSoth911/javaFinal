package user;
public interface IStaff {
   String getUsername();
   String getPhoneNumber();
   int getId();
   String getEmail();
   boolean checkPassword(String password);

   boolean can(String action);
}
