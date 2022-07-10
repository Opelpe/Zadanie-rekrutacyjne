package com.pepe.rekrutacjagopos;

import android.app.Application;

import com.pepe.rekrutacjagopos.data.local.ObjectBox;
import com.pepe.rekrutacjagopos.data.remote.items.ItemsRepository;
import com.pepe.rekrutacjagopos.di.AppComponent;
import com.pepe.rekrutacjagopos.di.DaggerAppComponent;

import javax.inject.Inject;

public class App extends Application {
    public AppComponent appComponent = DaggerAppComponent.create();

    @Inject
    ItemsRepository itemsRepository;

    @Override
    public void onCreate() {
        super.onCreate();
        ObjectBox.init(getApplicationContext());
    }
}
