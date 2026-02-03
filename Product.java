public class Product{
    static int count=1;
    int id;
    String category;
    String name;
    int quantity;
    double importPrice;
    String importDate;
    double exportPrice;
    String expiredDate;


    public Product(String category,String name,int quantity,double importPrice,String importDate,double exportPrice,String expiredDate){
        this.id=count++;
        this.category=category;
        this.name=name;
        this.quantity=quantity;
        this.importPrice=importPrice;
        this.importDate=importDate;
        this.exportPrice=exportPrice;
        this.expiredDate=expiredDate;
    }
}