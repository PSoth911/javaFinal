
package user;

import main.StockManagement;
public class Cashier extends Staff {
    
    @Override
    public boolean can(String action) {
        if (action.equals(StockManagement.SELL_PRODUCT)
        || action.equals(StockManagement.COMPLETE_ORDER)
        || action.equals(StockManagement.VIEW_RECIPT)) {
        return true;
        }
        return false;
    }
    public Cashier(String username, String password, String phoneNumber, String email, String dateHired, float salary){ 
        super(username, password, phoneNumber, email, dateHired, salary);
    }

    
    @Override
    public String toString() {
        return super.toString() + " Cashier";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        if (!super.equals(obj)) return false;
        Cashier other = (Cashier) obj;
        return Float.floatToIntBits(super.getSalary())== Float.floatToIntBits(other.getSalary());
        }    
    }
