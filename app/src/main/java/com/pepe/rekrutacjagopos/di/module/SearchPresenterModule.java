package com.pepe.rekrutacjagopos.di.module;

import androidx.annotation.NonNull;

import com.pepe.rekrutacjagopos.ui.items.ItemsContract;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public class SearchPresenterModule {
    @NonNull
    private final ItemsContract.View view;

    public SearchPresenterModule(@NonNull ItemsContract.View view) {
        this.view = view;
    }


    //I have to figure it out, how to do that this way

//    @Provides
//    ItemsContract.View providesItemsContractView(){
//        return view;
//    }

}