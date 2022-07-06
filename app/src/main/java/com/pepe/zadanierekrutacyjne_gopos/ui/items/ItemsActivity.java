package com.pepe.zadanierekrutacyjne_gopos.ui.items;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.pepe.zadanierekrutacyjne_gopos.R;

public class ItemsActivity extends AppCompatActivity implements ItemsContract.View {
    private static final String TAG = ItemsActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}