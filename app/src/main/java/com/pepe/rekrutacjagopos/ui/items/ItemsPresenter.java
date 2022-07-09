package com.pepe.rekrutacjagopos.ui.items;

import android.util.Log;

import com.pepe.rekrutacjagopos.data.remote.items.ItemsRepository;

import javax.inject.Inject;

public class ItemsPresenter implements ItemsContract.Presenter {
    private static final String TAG = ItemsPresenter.class.getSimpleName();
    private final ItemsRepository itemsRepository;

    private ItemsRepository.ItemsListener itemsListener = itemsUIModel -> Log.d(TAG, "Get items: " + itemsUIModel.name);

    @Inject
    public ItemsPresenter(ItemsRepository itemsRepository) {
        this.itemsRepository = itemsRepository;
    }

    @Override
    public void viewCreated() {
        Log.d(TAG, "View created");

        itemsRepository.setFormattedItemsListener(itemsListener);
        itemsRepository.getItems();
    }
}
