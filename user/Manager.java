package user;

import java.time.LocalDate;

public class Manager extends Staff{

    @Override
    public boolean can(String action) {
        // TODO Auto-generated method stub
        // just test code
        // if (action.equals(StockManagement.VIEW_PRODUCTS)) {
        //     return false;
        // }
        return true;
    }
    public Manager(Staff s){ 
        super(s.getUsername(),s.getPassword(),s.getPhoneNumber(), s.getEmail(),LocalDate.now().toString(),s.getId());
    }



    

    @Override

    public String toString() {
        return super.toString() +
           " Manager [position: Manager" +
             "]";
    }


    @Override

    public boolean equals(Object obj) {   
    Manager other = (Manager) obj;
     if (!super.equals(obj))
        {
            return false;
        } else {

           
        return true;
    }

    }
}