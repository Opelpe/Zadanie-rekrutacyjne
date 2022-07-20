package com.pepe.rekrutacjagopos.di;

import com.pepe.rekrutacjagopos.App;
import com.pepe.rekrutacjagopos.di.module.ItemsModule;
import com.pepe.rekrutacjagopos.di.module.NetworkModule;
import com.pepe.rekrutacjagopos.ui.items.ItemsActivity;
import com.pepe.rekrutacjagopos.ui.items.ItemsContract;

import dagger.Binds;
import dagger.Component;

@Component(modules = {NetworkModule.class, ItemsModule.class})
public interface AppComponent {

    void inject(ItemsActivity itemsActivity);

}

