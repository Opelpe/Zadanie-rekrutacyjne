package com.pepe.rekrutacjagopos.ui.items;

import android.util.Log;

import com.pepe.rekrutacjagopos.data.local.LocalDataSource;
import com.pepe.rekrutacjagopos.data.local.ObjectBox;
import com.pepe.rekrutacjagopos.data.local.model.LocalItemModel;
import com.pepe.rekrutacjagopos.data.model.ui.ItemsUIModel;
import com.pepe.rekrutacjagopos.data.remote.items.ItemsRepository;

import java.util.List;

import javax.inject.Inject;

import io.objectbox.Box;

public class ItemsPresenter implements ItemsContract.Presenter {
    private static final String TAG = ItemsPresenter.class.getSimpleName();
    private final ItemsRepository itemsRepository;
    private ItemsContract.View view;

    private ItemsRepository.ItemsUIListener listener = new ItemsRepository.ItemsUIListener() {
        @Override
        public void onItemsPrepared(List<ItemsUIModel> items) {
      view.setProductsView(items);
        }
    };


    @Inject
    public ItemsPresenter(ItemsRepository itemsRepository) {//}, ItemsContract.View view) {
        this.itemsRepository = itemsRepository;
//        this.view = null;
    }

    private ItemsRepository.ItemsUIListener ItemsUIListener = new ItemsRepository.ItemsUIListener() {
        @Override
        public void onItemsPrepared(List<ItemsUIModel> items) {
            view.setProductsView(items);
        }
    };



    @Override
    public void viewCreated(ItemsContract.View view) {
        Log.d(TAG, "VIEW HAS BEEN CREATED!");

        this.view = view;

        itemsRepository.setUIItemsListener(listener);
        itemsRepository.getItems();

    }
}
