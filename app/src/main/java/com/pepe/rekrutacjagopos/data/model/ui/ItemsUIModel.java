package com.pepe.rekrutacjagopos.data.model.ui;

public class ItemsUIModel {

    public String name;
    public String category;
    public String price;
    public String tax;

    public ItemsUIModel(String name, String category, String price, String tax) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.tax = tax;
    }

    public ItemsUIModel() {
    }
}
