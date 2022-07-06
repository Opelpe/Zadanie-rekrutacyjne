package com.pepe.rekrutacjagopos.di;

import com.pepe.rekrutacjagopos.ui.items.ItemsActivity;

import dagger.Component;

@Component
public interface AppComponent {

    void inject(ItemsActivity itemsActivity);
}
