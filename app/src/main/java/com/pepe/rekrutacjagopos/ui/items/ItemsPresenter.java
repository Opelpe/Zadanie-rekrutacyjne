package com.pepe.rekrutacjagopos.ui.items;

import android.util.Log;

import javax.inject.Inject;

public class ItemsPresenter implements ItemsContract.Presenter {
    private static final String TAG = ItemsPresenter.class.getSimpleName();


    @Inject
    public ItemsPresenter() {

    }

    @Override
    public void viewCreated() {
        Log.d(TAG, "dupa");
    }
}