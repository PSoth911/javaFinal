package user;

import main.StockManagement;
public class Stocker extends Cashier { 
    @Override
    public boolean can(String action) {
        // TODO Auto-generated method stub
        if (action.equals(StockManagement.VIEW_PRODUCTS) || action.equals(StockManagement.UPDATE_STOCK)) {
            return true;
        }
        return false;
    }

    public Stocker(String username, String password, String phoneNumber, String email, String dateHired, float salary){      
       super(username, password, phoneNumber, email, dateHired, salary);
    }
    @Override
    public String toString() {
        return super.toString() + " Stocker";
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        return super.equals(obj);
        }
    }
