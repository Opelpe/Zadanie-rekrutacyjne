package com.pepe.rekrutacjagopos.ui.items;

import android.util.Log;

import com.pepe.rekrutacjagopos.data.model.ui.ItemModelUI;
import com.pepe.rekrutacjagopos.data.remote.items.ItemsRepository;

import java.util.List;

import javax.inject.Inject;

public class ItemsPresenter implements ItemsContract.Presenter {
    private static final String TAG = ItemsPresenter.class.getSimpleName();
    private final ItemsRepository itemsRepository;
    private ItemsContract.View view;

    private ItemsRepository.ProductListener listener = new ItemsRepository.ProductListener() {
        @Override
        public void onItemsLoaded(List<ItemModelUI> items) {
      view.setProductsView(items);
        }
    };


    @Inject
    public ItemsPresenter(ItemsRepository itemsRepository) {//}, ItemsContract.View view) {
        this.itemsRepository = itemsRepository;
//        this.view = null;
    }

    @Override
    public void viewCreated(ItemsContract.View view) {
        Log.d(TAG, "VIEW HAS BEEN CREATED!");

        this.view = view;

        itemsRepository.setUIItemsListener(listener);
        itemsRepository.getItems();

    }
}
