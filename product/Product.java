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


    public Product(String category,String name,int quantity,double importPrice,LocalDate importDate,double exportPrice,LocalDate expiredDate){
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






    




    



    
    
    
    
   






}