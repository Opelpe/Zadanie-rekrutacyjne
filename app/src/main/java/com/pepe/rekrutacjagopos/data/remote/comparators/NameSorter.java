package com.pepe.rekrutacjagopos.data.remote.comparators;

import com.pepe.rekrutacjagopos.data.remote.model.item.ItemRetrofitModel;

import java.util.Comparator;

public class NameSorter implements Comparator<ItemRetrofitModel> {

    @Override
    public int compare(ItemRetrofitModel t1, ItemRetrofitModel t2) {
        return t1.name.compareTo(t2.name);
    }
}
