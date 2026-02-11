package product;
public class Product{
    private static int count=1;
    private int id;
    private String category;
    private String name;
    private int quantity;
    private double importPrice;
    private String importDate;
    private double exportPrice;
    private String expiredDate;


    public Product(String category,String name,int quantity,double importPrice,String importDate,double exportPrice,String expiredDate){
       this.setId();
       this.setCategory(category);
       this.setName(name);
       this.setQuantity(quantity);
       this.setImportPrice(importPrice);
       this.setImportDate(importDate);
       this.setExportPrice(exportPrice);
       this.setExpiredDate(expiredDate);
    }

    
    public void setId() {
        this.id = count++;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public void setImportPrice(double importPrice) {
        this.importPrice = importPrice;
    }
    public void setImportDate(String importDate) {
        this.importDate = importDate;
    }
    public void setExportPrice(double exportPrice) {
        this.exportPrice = exportPrice;
    }
    public void setExpiredDate(String expiredDate) {
        this.expiredDate = expiredDate;
    }

    public int getId() {
        return id;
    }
    public String getCategory() {
        return category;
    }
    public String getName() {
        return name;
    }
    public int getQuantity() {
        return quantity;
    }
    public double getImportPrice() {
        return importPrice;
    }
    public String getImportDate() {
        return importDate;
    }
    public double getExportPrice() {
        return exportPrice;
    }
    public String getExpiredDate() {
        return expiredDate;
    }






    




    



    
    
    
    
   






}