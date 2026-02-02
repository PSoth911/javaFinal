public class Product{
    int id;
    String category;
    String name;
    int quantity;
    double importPrice;
    int importDate;
    double exportPrice;
    int expiredDate;


    public Product(String category,String name,int quantity,double importPrice,int importDate,double exportPrice,int expiredDate){
        this.id=id++;
        this.category=category;
        this.name=name;
        this.quantity=quantity;
        this.importPrice=importPrice;
        this.importDate=importDate;
        this.exportPrice=exportPrice;
        this.expiredDate=expiredDate;
    }
}