package com.pepe.rekrutacjagopos.data.remote.items;

import com.pepe.rekrutacjagopos.data.local.ItemsLocalDataSource;
import com.pepe.rekrutacjagopos.data.remote.items.ItemsRemoteDataSource;
import com.pepe.rekrutacjagopos.data.remote.token.TokenRemoteDataSource;

import javax.inject.Inject;

public class ItemsRepository {
    private final com.pepe.rekrutacjagopos.data.local.ItemsLocalDataSource itemsLocalDataSource;
    private final ItemsRemoteDataSource itemsRemoteDataSource;

    @Inject
    public ItemsRepository(ItemsLocalDataSource itemsLocalDataSource, ItemsRemoteDataSource itemsRemoteDataSource) {
        this.itemsLocalDataSource = itemsLocalDataSource;
        this.itemsRemoteDataSource = itemsRemoteDataSource;
    }

    public void getItems() {
        itemsRemoteDataSource.getItems();
    }
}
