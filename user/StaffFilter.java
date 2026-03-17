package user;
@FunctionalInterface
public interface StaffFilter {
    boolean test(Staff s);
}
