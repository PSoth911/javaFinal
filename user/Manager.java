package user;

public class Manager extends Cashier{
    private float bonus;

    public float getBonus() {
        return bonus;
    }
    public void setBonus(float bonus) {
        if (bonus < 0) {
            this.bonus = 0;
        } else {
            this.bonus = bonus;
        }
    }

    @Override
    public boolean can(String action) {
        return true;
    }
    public Manager(String username, String password, String phoneNumber,String email, String dateHired, float salary, float bonus){ 
        super(username, password, phoneNumber, email, dateHired, salary);
        this.setBonus(bonus);
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