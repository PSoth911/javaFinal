public class Product{
    static int count=1;
    public int id;
    public String category;
    public String name;
    public int quantity;
    public double importPrice;
    public String importDate;
    public double exportPrice;
    public String expiredDate;


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