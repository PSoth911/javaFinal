import java.util.ArrayList;

public class AdminList{
    ArrayList<AdminAccount> adminList = new ArrayList<>();

    void addAdminAcc(String username, String password, String phoneNumber,String email){
        adminList.add(new AdminAccount(username,password,phoneNumber,email));
    }

    void printAdmin(){
        for (AdminAccount a : adminList){
            System.out.print(a.username+"\t"+a.password+"\t"+a.phoneNumber+"\t"+a.email);
        }
    }
    

}