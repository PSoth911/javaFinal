package product;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ManageProduct {

    public ArrayList<Product> items = new ArrayList<>();

    public void addItem(String category,String name,int quantity,double importPrice,String importDateStr,double exportPrice,String expiredDateStr) {
        DateTimeFormatter convert = DateTimeFormatter.ofPattern("d/M/yyyy");
        LocalDate importDate = LocalDate.parse(importDateStr,convert);
        LocalDate expiredDate = LocalDate.parse(expiredDateStr,convert);
        items.add(new Product(category, name, quantity, importPrice, importDate, exportPrice, expiredDate));
    }

    public void deleteItem(int id){
       for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getId()== id) {
                items.remove(i);
                break;
            }
        }
    }

    public void increaseItemByValue(int id,int value){
       for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getId()== id) {
                items.get(i).setQuantity(items.get(i).getQuantity() + value);
                break;
            }
        }
    }
    public void decreaseItemByValue(int id,int value){
       for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getId()== id) {
                if (items.get(i).getQuantity()>=value){
                    items.get(i).setQuantity(items.get(i).getQuantity() - value);
                    break;
                }else if(items.get(i).getQuantity()==0){
                    System.out.println("Item is zero");
                }else{
                    System.out.println("Item's quantity < Decrease");
                }
            }
        }
    }

    public void increaseItem(int id){
       for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getId()== id) {
                items.get(i).setQuantity(items.get(i).getQuantity() + 1);
                break;
            }
        }
    }
    public void decreaseItem(int id){
       for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getId()== id) {
                if (items.get(i).getQuantity()>0){
                    items.get(i).setQuantity(items.get(i).getQuantity() - 1);
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
            System.out.println(item.getId() + "\t" +item.getCategory() + "\t\t" + item.getName() + "\t" + item.getQuantity()+ "\t\t" + item.getImportPrice()+ "\t\t" +item.getImportDate()+ "\t\t" + item.getExportPrice() + "\t\t" + item.getExpiredDate());
        }
    }
}
