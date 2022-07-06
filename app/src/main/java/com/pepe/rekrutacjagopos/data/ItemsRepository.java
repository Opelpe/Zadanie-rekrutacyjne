package com.pepe.rekrutacjagopos.data;

import com.pepe.rekrutacjagopos.data.local.ItemsLocalDataSource;
import com.pepe.rekrutacjagopos.data.remote.ItemsRemoteDataSource;

import javax.inject.Inject;

public class ItemsRepository {
    private final com.pepe.rekrutacjagopos.data.local.ItemsLocalDataSource itemsLocalDataSource;
    private final com.pepe.rekrutacjagopos.data.remote.ItemsRemoteDataSource itemsRemoteDataSource;

    @Inject
    public ItemsRepository(ItemsLocalDataSource itemsLocalDataSource, ItemsRemoteDataSource itemsRemoteDataSource) {
        this.itemsLocalDataSource = itemsLocalDataSource;
        this.itemsRemoteDataSource = itemsRemoteDataSource;
    }
}
