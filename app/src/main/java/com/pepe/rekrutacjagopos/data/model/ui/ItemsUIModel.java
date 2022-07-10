package com.pepe.rekrutacjagopos.data.model.ui;

import com.pepe.rekrutacjagopos.data.adapters.ItemsAdapter;

public class ItemsUIModel {

    public String defaultImage;
    public String name;
    public String category;
    public String price;
    public String tax;


    public ItemsUIModel(String name, String category, String price, String tax,  String defaultImage) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.tax = tax;
        this.defaultImage = defaultImage;
    }

    public ItemsUIModel(String name, String category, String price, String tax) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.tax = tax;
    }

    public ItemsUIModel() {
    }
}
