package com.pepe.rekrutacjagopos.data.remote;

import javax.inject.Inject;

public class ItemsRemoteDataSource {


    private final ItemsRetrofitService itemsRetrofitService;

    @Inject
    public ItemsRemoteDataSource(ItemsRetrofitService itemsRetrofitService) {
        this.itemsRetrofitService = itemsRetrofitService;
    }
}
