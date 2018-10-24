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

    @Digits(integer=30, fraction=0)
    private double sku;

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
    private int initialStock;


    ///
    public Inventory(String name, String description, int sku, double purchaseCost, double salePrice, String vendor, int initialStock) {
        this.name = name;
        this.description = description;
        this.sku = sku;
        this.purchaseCost = purchaseCost;
        this.salePrice = salePrice;
        this.vendor = vendor;
        this.initialStock = initialStock;
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

    public double getSku() {
        return sku;
    }

    public void setSku(int sku) {
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

    public int getInitialStock() {
        return initialStock;
    }

    public void setInitialStock(int initialStock) {
        this.initialStock = initialStock;
    }

}
