package com.pepe.rekrutacjagopos.data.remote.items;

import android.util.Log;

import com.pepe.rekrutacjagopos.data.local.LocalDataSource;
import com.pepe.rekrutacjagopos.data.local.ObjectBox;
import com.pepe.rekrutacjagopos.data.local.model.LocalItemModel;
import com.pepe.rekrutacjagopos.data.model.ui.ItemsUIModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import io.objectbox.Box;

public class ItemsRepository {

    private final LocalDataSource localDataSource;
    private final ItemsRemoteDataSource itemsRemoteDataSource;
    private ItemsUIListener itemsUIListener;

    private static final String TAG = ItemsRepository.class.getSimpleName();


    private LocalDataSource.ProductsListener listener = new LocalDataSource.ProductsListener() {
        @Override
        public void onItemsLoaded(List<LocalItemModel> itemsList) {
            Box<LocalItemModel> localBox = ObjectBox.get().boxFor(LocalItemModel.class);
            List<LocalItemModel> localList = localBox.getAll();

            if (itemsList.size() != localList.size()) {
                Log.d(TAG, "Items loaded, compare items with local list, NOT EQUAL SIZE: " + itemsList.size() + "  Remote: " + localList.size());
                localBox.removeAll();
                localBox.put(itemsList);
            }

            formatData(localBox.getAll(), itemsList);
            Log.d(TAG, "Items loaded, compare items with local list, SIZE: " + itemsList.size() + "\n REMOTE: " + localBox.getAll().size());
//            itemsUIModel = new ItemsUIModel(formatName(retrofitResponse.get(i).name), formatCategory(retrofitResponse.get(i).category),
////                                formatPrice(retrofitResponse.get(i).price.amount, retrofitResponse.get(i).price.currency),
////                                formatTax(retrofitResponse.get(i).tax), retrofitResponse.get(i).imageModel.smallImage);
        }
    };

    public interface ItemsUIListener {
        void onItemsPrepared(List<ItemsUIModel> items);
    }

    public void setUIItemsListener(ItemsUIListener listener) {
        itemsUIListener = listener;
    }


    @Inject
    public ItemsRepository(LocalDataSource localDataSource, ItemsRemoteDataSource itemsRemoteDataSource) {
        this.localDataSource = localDataSource;
        this.itemsRemoteDataSource = itemsRemoteDataSource;

    }

    public void getItems() {
        Log.d("getItems REPO", "getites started");
        localDataSource.setProductsListener(listener);
        itemsRemoteDataSource.getItems();
    }

    private void formatData(List<LocalItemModel> itemModel, List<LocalItemModel> itemsList) {
        Box<ItemsUIModel> uiModelBox = ObjectBox.get().boxFor(ItemsUIModel.class);
        Box<LocalItemModel> localBox = ObjectBox.get().boxFor(LocalItemModel.class);

        Log.d(TAG, "Local DATA Source FORMAT DATA: " + itemModel.size());


        List<ItemsUIModel> itemsUIModelList = new ArrayList<>();

        for (int i = 0; i < itemModel.size(); i++) {

            ItemsUIModel itemsUIModel;

            if (itemModel.get(i).imageURL != null) {
                if (!itemModel.get(i).name.equals(itemsList.get(i).name)) {
                    updateLDB(itemsList.get(i), i);
                }
                if (itemModel.get(i).priceAmount != itemsList.get(i).priceAmount) {
                    updateLDB(itemsList.get(i), i);
                }
                if (itemModel.get(i).category != itemsList.get(i).category) {
                    updateLDB(itemsList.get(i), i);
                }
                if (itemModel.get(i).tax != itemsList.get(i).tax) {
                    updateLDB(itemsList.get(i), i);
                }
                if (!itemModel.get(i).currency.equals(itemsList.get(i).currency)) {
                    updateLDB(itemsList.get(i), i);
                }
                if (!itemModel.get(i).imageURL.equals(itemsList.get(i).imageURL)) {
                    updateLDB(itemsList.get(i), i);
                }
                if (!itemsList.isEmpty() || itemsList.size() != 0) {
                    if (!itemModel.get(i).name.equals(itemsList.get(i).name)
                            || itemModel.get(i).priceAmount != itemsList.get(i).priceAmount
                            || itemModel.get(i).category != itemsList.get(i).category
                            || itemModel.get(i).tax != itemsList.get(i).tax
                            || !itemModel.get(i).currency.equals(itemsList.get(i).currency)
                            || !itemModel.get(i).imageURL.equals(itemsList.get(i).imageURL)) {
                        updateLDB(itemModel.get(i), i);
                    }
                } else {
                    itemsUIModel = new ItemsUIModel(formatName(itemModel.get(i).name), formatCategory(itemModel.get(i).category),
                            formatPrice(itemModel.get(i).priceAmount, itemModel.get(i).currency), formatTax(itemModel.get(i).tax), itemModel.get(i).imageURL);
                    itemsUIModelList.add(itemsUIModel);
                    uiModelBox.put(itemsUIModel);
                    itemsUIListener.onItemsPrepared(itemsUIModelList);
                }


            } else {
                if (!itemModel.get(i).name.equals(itemsList.get(i).name)) {
                    updateLDB(itemsList.get(i), i);
                }
                if (itemModel.get(i).priceAmount != itemsList.get(i).priceAmount) {
                    updateLDB(itemsList.get(i), i);
                }
                if (itemModel.get(i).category != itemsList.get(i).category) {
                    updateLDB(itemsList.get(i), i);
                }
                if (itemModel.get(i).tax != itemsList.get(i).tax) {
                    updateLDB(itemsList.get(i), i);
                }
                if (!itemModel.get(i).currency.equals(itemsList.get(i).currency)) {
                    updateLDB(itemsList.get(i), i);
                }


                if (!itemsList.isEmpty() || itemsList.size() != 0) {
                    if (!itemModel.get(i).name.equals(itemsList.get(i).name)
                            || itemModel.get(i).priceAmount != itemsList.get(i).priceAmount
                            || itemModel.get(i).category != itemsList.get(i).category
                            || itemModel.get(i).tax != itemsList.get(i).tax
                            || !itemModel.get(i).currency.equals(itemsList.get(i).currency)) {
                        updateLDB(itemModel.get(i), i);
                    }
                } else {
                    itemsUIModel = new ItemsUIModel(formatName(itemModel.get(i).name), formatCategory(itemModel.get(i).category),
                            formatPrice(itemModel.get(i).priceAmount, itemModel.get(i).currency), formatTax(itemModel.get(i).tax));
                    itemsUIModelList.add(itemsUIModel);
                    uiModelBox.put(itemsUIModel);
                    itemsUIListener.onItemsPrepared(itemsUIModelList);
                }
            }

        }

    }

    private void updateLDB(LocalItemModel localItemModel, int i) {
        Box<LocalItemModel> box = ObjectBox.get().boxFor(LocalItemModel.class);

        box.getAll().get(i).updateName(localItemModel.name);
        box.getAll().get(i).updatePriceAmount(localItemModel.priceAmount);
        box.getAll().get(i).updateCategory(localItemModel.category);
        box.getAll().get(i).updateCurrency(localItemModel.currency);
        box.getAll().get(i).updateImageURL(localItemModel.imageURL);
        box.getAll().get(i).updateTax(localItemModel.tax);
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
