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

    public Stocker(Staff s ,float salary){ 
        
       super(s, salary);
    }
  

    @Override

    public String toString() {
        return super.toString() +
           " Stocker [position: Stocker" +
           ", salary=" + getSalary() + "]";
    }


    @Override

    public boolean equals(Object obj) {   
    Stocker other = (Stocker) obj;
     if (!super.equals(obj))
        {
            return false;
        } else {

            if (Float.floatToIntBits(getSalary()) 
                != Float.floatToIntBits(other.getSalary()))
            {
                return false;
            }
        }
        return true;
    }
}
