import java.util.ArrayList;

public class ManageProduct {

    public ArrayList<Product> items = new ArrayList<>();

    public void addItem(String category,String name,int quantity,double importPrice,String importDate,double exportPrice,String expiredDate) {
        items.add(new Product(category, name, quantity, importPrice, importDate, exportPrice, expiredDate));
    }

    public void deleteItem(int id){
       for (int i = 0; i < items.size(); i++) {
            if (items.get(i).id== id) {
                items.remove(i);
                Product.count--;
                break;
            }
        }
    }

    public void increaseItemByValue(int id,int value){
       for (int i = 0; i < items.size(); i++) {
            if (items.get(i).id== id) {
                items.get(i).quantity+=value;
                break;
            }
        }
    }
    public void decreaseItemByValue(int id,int value){
       for (int i = 0; i < items.size(); i++) {
            if (items.get(i).id== id) {
                if (items.get(i).quantity>=value){
                    items.get(i).quantity-=value;
                    break;
                }else if(items.get(i).quantity==0){
                    System.out.println("Item is zero");
                }else{
                    System.out.println("Item's quantity < Decrease");
                }
            }
        }
    }

    public void increaseItem(int id){
       for (int i = 0; i < items.size(); i++) {
            if (items.get(i).id== id) {
                items.get(i).quantity++;
                break;
            }
        }
    }
    public void decreaseItem(int id){
       for (int i = 0; i < items.size(); i++) {
            if (items.get(i).id== id) {
                if (items.get(i).quantity>0){
                    items.get(i).quantity--;
                    break;
                }else{
                    System.out.println("Item's quantity is 0");
                    break;
                }
            }
        }
    }

    public static void printItems(ArrayList<Product> items){
        System.out.println("ID\tCategory\tName\tQuantity\tImport Price\tImport Date\tExport Price\t\tExpired Date");
        for (Product item : items){
            System.out.println(item.id + "\t" +item.category + "\t\t" + item.name + "\t" + item.quantity+ "\t\t" + item.importPrice+ "\t\t" +item.importDate+ "\t\t" + item.exportPrice + "\t\t" + item.expiredDate);
        }
    }
}
