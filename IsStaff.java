public interface IsStaff {
   String getUsername();
   String getPhoneNumber();
   int getId();
   String getPosition();

   boolean can(String action);
}
