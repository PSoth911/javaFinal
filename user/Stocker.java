package user;
import java.time.LocalDate;

import main.StockManagement;

public class Stocker extends Staff {
    private int id;        
    private static int count=1;   
    private float salary;   


    @Override
    public boolean can(String action) {
        // TODO Auto-generated method stub
        if (action.equals(StockManagement.VIEW_PRODUCTS) || action.equals(StockManagement.UPDATE_STOCK)) {
            return true;
        }
        return false;
    }

Stocker(Staff s ,float salary){ 
        this.id=count++;
        super(s.getUsername(),s.getPassword(),s.getPhoneNumber(), s.getEmail(),LocalDate.now().toString());
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
           " Stocker [position: Stocker, id=" + id +
           ", salary=" + getSalary() + "]";
    }


    @Override

    public boolean equals(Object obj) {   
    Stocker other = (Stocker) obj;
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
