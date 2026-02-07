
import java.util.ArrayList;
public class PrintProduct{
    static void printItems(ArrayList<Product> items){
        System.out.println("ID\tCategory\tName\tQuantity\tImport Price\tImport Date\tExport Price\t\tExpired Date");
        for (Product item : items){
            System.out.println(item.id + "\t" +item.category + "\t\t" + item.name + "\t" + item.quantity+ "\t\t" + item.importPrice+ "\t\t" +item.importDate+ "\t\t" + item.exportPrice + "\t\t" + item.expiredDate);
        }
    }
}