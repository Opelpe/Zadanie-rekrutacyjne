package com.pepe.rekrutacjagopos.data.model.ui;

import com.pepe.rekrutacjagopos.data.adapters.ItemsAdapter;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

@Entity
public class ItemModelUI {

    @Id long id;

    public String defaultImage;
    public String name;
    public String category;
    public String price;
    public String tax;


    public ItemModelUI(String name, String category, String price, String tax, String defaultImage) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.tax = tax;
        this.defaultImage = defaultImage;
    }

    public ItemModelUI(String name, String category, String price, String tax) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.tax = tax;
    }

    public ItemModelUI() {
    }
}
