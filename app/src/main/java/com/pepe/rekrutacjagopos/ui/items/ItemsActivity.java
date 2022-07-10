package com.pepe.rekrutacjagopos.ui.items;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.pepe.rekrutacjagopos.App;
import com.pepe.rekrutacjagopos.R;
import com.pepe.rekrutacjagopos.data.adapters.ItemsAdapter;
import com.pepe.rekrutacjagopos.data.model.ui.ItemsUIModel;
import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

public class ItemsActivity extends AppCompatActivity implements ItemsContract.View {
    private static final String TAG = ItemsActivity.class.getSimpleName();
    private RecyclerView productsRV;

    @Inject
    ItemsContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((App) getApplicationContext()).appComponent.inject(this);

        setContentView(R.layout.activity_main);

        productsRV = findViewById(R.id.itemsRV);
        productsRV.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        presenter.viewCreated(this);

    }

    @Override
    public void setProductsView(List<ItemsUIModel> itemsUIModel) {
        Log.d(TAG, "set Items View: " + itemsUIModel.size());
        ItemsAdapter adapter = new ItemsAdapter(itemsUIModel);
        productsRV.setAdapter(adapter);
    }
}

