package user;


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
    public Manager(String username, String password, String phoneNumber,String email, String dateHired){ 
        super(username, password, phoneNumber, email, dateHired);
    }

    @Override
    public String toString() {
        return super.toString() + " Manager{position=Manager}";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        if (!super.equals(obj)) return false;
        return true;
    }
}