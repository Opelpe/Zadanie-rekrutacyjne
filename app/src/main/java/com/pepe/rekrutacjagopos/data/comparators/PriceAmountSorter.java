package com.pepe.rekrutacjagopos.data.comparators;

import com.pepe.rekrutacjagopos.data.remote.model.item.ItemRetrofitModel;

import java.util.Comparator;

public class PriceAmountSorter implements Comparator<ItemRetrofitModel> {
    @Override
    public int compare(ItemRetrofitModel t1, ItemRetrofitModel t2) {
        return Integer.compare(t1.price.amount, t2.price.amount);
    }
}
