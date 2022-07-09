package com.pepe.rekrutacjagopos.data.remote.items;

import android.util.Log;

import com.pepe.rekrutacjagopos.data.model.ui.ItemsUIModel;
import com.pepe.rekrutacjagopos.data.local.ItemsLocalDataSource;
import com.pepe.rekrutacjagopos.data.remote.model.item.ItemRetrofitModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

public class ItemsRepository {
    private final com.pepe.rekrutacjagopos.data.local.ItemsLocalDataSource itemsLocalDataSource;
    private final ItemsRemoteDataSource itemsRemoteDataSource;

    private List<ItemRetrofitModel> parsedItems = new ArrayList();
    private ItemsListener itemsListener;

    public interface ItemsListener {
        void onItemsLoaded(ItemsUIModel itemsUIModel);
    }

    private final ItemsRemoteDataSource.dataListener dataListener = new ItemsRemoteDataSource.dataListener() {
        @Override
        public void onItemsLoaded(List<ItemRetrofitModel> retrofitResponse) {
            if (retrofitResponse != null) {
                parsedItems = retrofitResponse;
                for (int i = 0; i < parsedItems.size(); i++) {

                    ItemsUIModel itemsUIModel = new ItemsUIModel(formatName(parsedItems.get(i).name), formatCategory(parsedItems.get(i).category),
                            formatPrice(parsedItems.get(i).price.amount, parsedItems.get(i).price.currency), formatTax(parsedItems.get(i).tax));

                    itemsListener.onItemsLoaded(itemsUIModel);
                }
            }
        }
    };

    public void setFormattedItemsListener(ItemsListener listener) {
        itemsListener = listener;
    }

    @Inject
    public ItemsRepository(ItemsLocalDataSource itemsLocalDataSource, ItemsRemoteDataSource itemsRemoteDataSource) {
        this.itemsLocalDataSource = itemsLocalDataSource;
        this.itemsRemoteDataSource = itemsRemoteDataSource;

    }

    public void getItems() {
        Log.d("getItems REPO", "getites started");

        itemsRemoteDataSource.setItemsListener(dataListener);
        itemsRemoteDataSource.getItems();
    }

    private String formatTax(int tax) {
        if (tax == 1) {
            return "23%";
        } else {
            if (tax == 2) {
                return "8%";
            } else {
                return "";
            }
        }
    }

    private String formatCategory(int category) {
        if (category == 1) {
            return "NAPOJE";
        } else {
            if (category == 2) {
                return "FAST FOOD";
            } else {
                if (category == 3) {
                    return "DESERY";
                } else {
                    return "";
                }
            }
        }
    }

    private String formatPrice(int amount, String currency) {

        StringBuilder builder = new StringBuilder();
        return builder.append(amount)
                .append(" ")
                .append(currency)
                .toString();
    }

    private String formatName(String name) {
        return name.toUpperCase(Locale.ROOT);
    }

}
