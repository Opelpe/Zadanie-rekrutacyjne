package com.pepe.rekrutacjagopos.data.comparators;

import com.pepe.rekrutacjagopos.data.remote.model.item.ItemRetrofitModel;

import java.util.Comparator;

public class CategorySorter implements Comparator<ItemRetrofitModel> {
    @Override
    public int compare(ItemRetrofitModel t1, ItemRetrofitModel t2) {
        return Integer.compare(t2.category, t1.category);
    }
}
