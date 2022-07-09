package com.pepe.rekrutacjagopos.data.remote.items;

import android.util.Log;

import com.pepe.rekrutacjagopos.data.local.ItemsLocalDataSource;
import com.pepe.rekrutacjagopos.data.remote.items.ItemsRemoteDataSource;
import com.pepe.rekrutacjagopos.data.remote.model.item.GetItemsRetrofitResponse;
import com.pepe.rekrutacjagopos.data.remote.model.item.ItemRetrofitModel;
import com.pepe.rekrutacjagopos.data.remote.token.TokenRemoteDataSource;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class ItemsRepository {
    private final com.pepe.rekrutacjagopos.data.local.ItemsLocalDataSource itemsLocalDataSource;
    private final ItemsRemoteDataSource itemsRemoteDataSource;

    private List<ItemRetrofitModel> parsedItems = new ArrayList();

    private ItemsRemoteDataSource.ItemsListener itemsListener = new ItemsRemoteDataSource.ItemsListener() {
        @Override
        public void onItemsLoaded(GetItemsRetrofitResponse retrofitResponse) {

            if (retrofitResponse !=null){
                parsedItems = retrofitResponse.items;

                Log.d("ITEMS REPOSITORY", "Items parsed: " + parsedItems.size());
            }

        }
    };
    @Inject
    public ItemsRepository(ItemsLocalDataSource itemsLocalDataSource, ItemsRemoteDataSource itemsRemoteDataSource) {
        this.itemsLocalDataSource = itemsLocalDataSource;
        this.itemsRemoteDataSource = itemsRemoteDataSource;

    }

    public void getItems() {
        Log.d("getItems REPO", "getites started");
                itemsRemoteDataSource.getItems();
    }
}
