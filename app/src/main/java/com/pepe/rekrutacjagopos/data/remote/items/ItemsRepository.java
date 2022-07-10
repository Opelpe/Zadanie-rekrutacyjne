package com.pepe.rekrutacjagopos.data.remote.items;

import android.util.Log;

import com.pepe.rekrutacjagopos.data.adapters.ItemsAdapter;
import com.pepe.rekrutacjagopos.data.comparators.CategorySorter;
import com.pepe.rekrutacjagopos.data.comparators.NameSorter;
import com.pepe.rekrutacjagopos.data.comparators.PriceAmountSorter;
import com.pepe.rekrutacjagopos.data.model.ui.ItemsUIModel;
import com.pepe.rekrutacjagopos.data.local.ItemsLocalDataSource;
import com.pepe.rekrutacjagopos.data.remote.model.item.ItemRetrofitModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

public class ItemsRepository {
    private final com.pepe.rekrutacjagopos.data.local.ItemsLocalDataSource itemsLocalDataSource;
    private final ItemsRemoteDataSource itemsRemoteDataSource;

    private static final String TAG = ItemsRepository.class.getSimpleName();

    private ItemsListener itemsListener;

    public interface ItemsListener {
        void onItemsLoaded(List<ItemsUIModel> itemsUIModel);
    }

    private final ItemsRemoteDataSource.dataListener dataListener = new ItemsRemoteDataSource.dataListener() {
        @Override
        public void onItemsLoaded(List<ItemRetrofitModel> retrofitResponse) {
            if (retrofitResponse != null) {

                retrofitResponse.sort(new CategorySorter()
                        .thenComparing(new PriceAmountSorter())
                        .thenComparing(new NameSorter()));

                List<ItemsUIModel> itemsUIModelList = new ArrayList<>();
                for (int i = 0; i < retrofitResponse.size(); i++) {

                    ItemsUIModel itemsUIModel;

                    if (retrofitResponse.get(i).imageModel != null) {
                        itemsUIModel = new ItemsUIModel(formatName(retrofitResponse.get(i).name), formatCategory(retrofitResponse.get(i).category),
                                formatPrice(retrofitResponse.get(i).price.amount, retrofitResponse.get(i).price.currency),
                                formatTax(retrofitResponse.get(i).tax), retrofitResponse.get(i).imageModel.smallImage);
                    }else {
                        itemsUIModel = new ItemsUIModel(formatName(retrofitResponse.get(i).name), formatCategory(retrofitResponse.get(i).category),
                                formatPrice(retrofitResponse.get(i).price.amount, retrofitResponse.get(i).price.currency), formatTax(retrofitResponse.get(i).tax));
                    }

                    itemsUIModelList.add(itemsUIModel);
                }
                itemsListener.onItemsLoaded(itemsUIModelList);
            }
        }
    };

    public void setItemsListener(ItemsListener listener) {
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
        return amount + " " + currency;
    }

    private String formatName(String name) {
        return name.toUpperCase(Locale.ROOT);
    }

}
