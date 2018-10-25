package org.launchcode.general_Store.models;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
public class Inventory {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=3, max=150)
    private String name;

    private String description;

    @NotNull
    @Size(min=3, max=30)
    private String sku;

    private String vendor;

    @NotNull
    @DecimalMin("0.01")
    @Digits(integer=8, fraction=2)
    private double purchaseCost;

    @NotNull
    @DecimalMin("0.01")
    @Digits(integer=8, fraction=2)
    private double salePrice;

    @Min(0)
    private int stock;


    ///
    public Inventory(String name, String description, String sku, double purchaseCost, double salePrice, String vendor, int stock) {
        this.name = name;
        this.description = description;
        this.sku = sku;
        this.purchaseCost = purchaseCost;
        this.salePrice = salePrice;
        this.vendor = vendor;
        this.stock = stock;
    }

    public Inventory() { }




//getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public double getPurchaseCost() {
        return purchaseCost;
    }

    public void setPurchaseCost(double purchaseCost) {
        this.purchaseCost = purchaseCost;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int Stock) {
        this.stock = Stock;
    }

}
