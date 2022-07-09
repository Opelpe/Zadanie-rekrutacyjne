package com.pepe.rekrutacjagopos.di;

import com.pepe.rekrutacjagopos.di.module.NetworkModule;
import com.pepe.rekrutacjagopos.ui.items.ItemsActivity;

import dagger.Component;

@Component(modules = {NetworkModule.class})
public interface AppComponent {

    void inject(ItemsActivity itemsActivity);


}
