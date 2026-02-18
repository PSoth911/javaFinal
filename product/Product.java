package product;

import java.time.LocalDate;

public class Product{
    private static int count=1;
    private int id;
    private String category;
    private String name;
    private int quantity;
    private double importPrice;
    private LocalDate importDate;
    private double exportPrice;
    private LocalDate expiredDate;
    private double discount;


    public Product(String category,String name,int quantity,double importPrice,LocalDate importDate,double exportPrice,LocalDate expiredDate){
       this.setId();
       this.setCategory(category);
       this.setName(name);
       this.setQuantity(quantity);
       this.setImportPrice(importPrice);
       this.setImportDate(importDate);
       this.setExportPrice(exportPrice);
       this.setExpiredDate(expiredDate);
       this.setDiscount();
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
        if (quantity >= 0) {
            this.quantity = quantity;
        }
    }
    public void setImportPrice(double importPrice) {
        if (importPrice > 0) {
            this.importPrice = importPrice;
        }
    }
    public void setImportDate(LocalDate importDate) {
        this.importDate = importDate;
    }
    public void setExportPrice(double exportPrice) {
        if (exportPrice > 0) {
            this.exportPrice = exportPrice;
        }
    }
    public void setExpiredDate(LocalDate expiredDate) {
        this.expiredDate = expiredDate;
    }
    public void setDiscount() {
        if (this.expiredDate.isEqual(LocalDate.now().plusDays(5))||this.expiredDate.isEqual(LocalDate.now().plusDays(4))){
            this.discount = 0.2;
        }else if (this.expiredDate.isEqual(LocalDate.now().plusDays(3))||this.expiredDate.isEqual(LocalDate.now().plusDays(2))||this.expiredDate.isEqual(LocalDate.now().plusDays(1))){
            this.discount = 0.5;
        }else if (this.expiredDate.isEqual(LocalDate.now())){
            this.discount = 0.7;
        }else {
            this.discount = 0.0;
        }
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
    public LocalDate getImportDate() {
        return importDate;
    }
    public double getExportPrice() {
        return exportPrice;
    }
    public LocalDate getExpiredDate() {
        return expiredDate;
    }
    public double getDiscount(){
        return discount;
    }
}