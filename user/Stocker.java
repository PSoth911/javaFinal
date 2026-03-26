package user;

import main.StockManagement;
public class Stocker extends Staff { 
    private String shift;
    @Override
    public boolean can(String action) {
        if (action.equals(StockManagement.VIEW_PRODUCTS) || action.equals(StockManagement.UPDATE_STOCK) || action.equals(StockManagement.ADD_ITEM) || action.equals(StockManagement.DELETE_ITEM)) {
            return true;
        }
        return false;
    }

    
    public Stocker(String username, String password, String phoneNumber, String email, String dateHired, float salary, String shift){ 
        super(username, password, phoneNumber, email, dateHired, salary);
        this.setShift(shift);
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        if (shift.equalsIgnoreCase("Morning") || shift.equalsIgnoreCase("Evening") || shift.equalsIgnoreCase("Night")) {
            this.shift = shift;
        } else {
            System.out.println("Invalid shift. Please enter Morning, Evening, or Night.");
        }
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
