package com.pepe.rekrutacjagopos.data;

import com.pepe.rekrutacjagopos.data.local.ItemsLocalDataSource;
import com.pepe.rekrutacjagopos.data.remote.ItemsRemoteDataSource;

import javax.inject.Inject;

public class ItemsRepository {
    private final com.pepe.rekrutacjagopos.data.local.ItemsLocalDataSource ItemsLocalDataSource;
    private final com.pepe.rekrutacjagopos.data.remote.ItemsRemoteDataSource ItemsRemoteDataSource;

    @Inject
    public ItemsRepository(ItemsLocalDataSource ItemsLocalDataSource, ItemsRemoteDataSource ItemsRemoteDataSource) {
        this.ItemsLocalDataSource = ItemsLocalDataSource;
        this.ItemsRemoteDataSource = ItemsRemoteDataSource;
    }
}
