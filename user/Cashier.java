
package user;

import main.StockManagement;
public class Cashier extends Manager {
    private float salary;
    @Override
    public boolean can(String action) {
        // TODO Auto-generated method stub
        if (action.equals(StockManagement.SELL_PRODUCT)
        || action.equals(StockManagement.COMPLETE_ORDER)
        || action.equals(StockManagement.VIEW_RECIPT)) {
        return true;
        }
        return false;
    }
    public Cashier(Staff s ,float salary){ 
        super(s);
        this.setSalary(salary);
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        if (salary < 0) {
            System.out.println("Salary cannot be negative.");
        } else {
            this.salary = salary;
        }
    }

    @Override

    public String toString() {
        return super.toString() +
           " Cashier [position: Cashier" +
           ", salary=" + getSalary() + "]";
    }


    @Override

    public boolean equals(Object obj) {   
    Cashier other = (Cashier) obj;
     if (!super.equals(obj))
        {
            return false;
        } else {

            if (Float.floatToIntBits(salary) 
                != Float.floatToIntBits(other.salary))
            {
                return false;
            }
        }
        return true;
    }

        
}
