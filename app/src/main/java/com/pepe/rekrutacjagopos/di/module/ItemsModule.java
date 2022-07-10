package com.pepe.rekrutacjagopos.di.module;

import com.pepe.rekrutacjagopos.ui.items.ItemsContract;
import com.pepe.rekrutacjagopos.ui.items.ItemsPresenter;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class ItemsModule {

    @Binds
    abstract ItemsContract.Presenter bindPresenter(ItemsPresenter itemsPresenter);

}
