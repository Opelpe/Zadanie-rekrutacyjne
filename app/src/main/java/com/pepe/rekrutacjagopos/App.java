package com.pepe.rekrutacjagopos;

import android.app.Application;

import com.pepe.rekrutacjagopos.di.AppComponent;
import com.pepe.rekrutacjagopos.di.DaggerAppComponent;

public class App extends Application {

    public AppComponent appComponent = DaggerAppComponent.create();
}
