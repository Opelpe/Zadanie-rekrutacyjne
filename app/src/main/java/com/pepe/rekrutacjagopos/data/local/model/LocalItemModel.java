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

    public void updateName(String name){
        this.name = name;
    }

    public void updatePriceAmount(int priceAmount) {
        this.priceAmount = priceAmount;
    }

    public void updateCurrency(String currency) {
        this.currency = currency;
    }

    public void updateCategory(int category) {
        this.category = category;
    }

    public void updateImageURL(String imageURL) {
        this.imageURL =imageURL;
    }

    public void updateTax(int tax) {
        this.tax = tax;
    }
}
