package com.pepe.rekrutacjagopos.ui.items;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.pepe.rekrutacjagopos.App;
import com.pepe.rekrutacjagopos.R;

import javax.inject.Inject;

public class ItemsActivity extends AppCompatActivity implements ItemsContract.View {
    private static final String TAG = ItemsActivity.class.getSimpleName();

    @Inject
    ItemsPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((App) getApplicationContext()).appComponent.inject(this);

        presenter.viewCreated();
    }
}

