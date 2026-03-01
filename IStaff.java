public interface IStaff {
   String getUsername();
   String getPhoneNumber();
   int getId();
   String getPosition();
   String getPassword();
   boolean can(String action);
}
