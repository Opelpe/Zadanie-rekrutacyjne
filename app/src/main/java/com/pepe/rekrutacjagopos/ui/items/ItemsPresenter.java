package com.pepe.rekrutacjagopos.ui.items;

import android.util.Log;

import com.pepe.rekrutacjagopos.data.model.ui.ItemsUIModel;
import com.pepe.rekrutacjagopos.data.remote.items.ItemsRepository;

import java.util.List;

import javax.inject.Inject;

public class ItemsPresenter implements ItemsContract.Presenter {
    private static final String TAG = ItemsPresenter.class.getSimpleName();
    private final ItemsRepository itemsRepository;
    private ItemsContract.View view;

    @Inject
    public ItemsPresenter(ItemsRepository itemsRepository) {//}, ItemsContract.View view) {
        this.itemsRepository = itemsRepository;
//        this.view = null;
    }

    private ItemsRepository.ItemsListener itemsListener = new ItemsRepository.ItemsListener() {
        @Override
        public void onItemsLoaded(List<ItemsUIModel> itemsUIModel) {
            Log.d(TAG, "ON ITEMS LOADED EXECUTED");
            view.setProductsView(itemsUIModel);
        }
    };

    @Override
    public void viewCreated(ItemsContract.View view) {
        Log.d(TAG, "VIEW HAS BEEN CREATED!");

        this.view = view;
        itemsRepository.setItemsListener(itemsListener);
        itemsRepository.getItems();
    }
}
