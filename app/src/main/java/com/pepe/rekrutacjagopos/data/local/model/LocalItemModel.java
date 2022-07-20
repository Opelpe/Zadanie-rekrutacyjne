package com.pepe.rekrutacjagopos.data.local.model;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

@Entity
public class LocalItemModel {

    @Id long id;

    public String name;
    public int category;
    public int priceAmount;
    public String currency;
    public int tax;
    public String imageURL;

    public LocalItemModel(String name, int category, int priceAmount, String currency, int tax, String imageURL) {
        this.name = name;
        this.category = category;
        this.priceAmount = priceAmount;
        this.currency = currency;
        this.tax = tax;
        this.imageURL = imageURL;
    }
    public LocalItemModel(String name, int category, int priceAmount, String currency, int tax) {
        this.name = name;
        this.category = category;
        this.priceAmount = priceAmount;
        this.currency = currency;
        this.tax = tax;
    }
    public LocalItemModel(){

    }

}
