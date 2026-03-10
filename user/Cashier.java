
package user;
import java.time.LocalDate;
import main.StockManagement;
public class Cashier extends Staff {
    private int id;        
    private static int count=1;      
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
        super(s.getUsername(),s.getPassword(),s.getPhoneNumber(), s.getEmail(),LocalDate.now().toString());
        this.id=count++;
        this.salary=salary;
    }
    public int getId(){
        return id;
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
           " Cashier [position: Cashier, id=" + id +
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
