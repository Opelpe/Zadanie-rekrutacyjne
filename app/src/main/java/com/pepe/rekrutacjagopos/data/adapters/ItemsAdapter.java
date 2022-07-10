package com.pepe.rekrutacjagopos.data.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pepe.rekrutacjagopos.R;
import com.pepe.rekrutacjagopos.data.model.ui.ItemsUIModel;
import com.pepe.rekrutacjagopos.di.module.NetworkModule;

import java.util.List;

public class ItemsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = ItemsAdapter.class.getSimpleName();

    private List<ItemsUIModel> itemsUIModel;

    public ItemsAdapter(List<ItemsUIModel> itemsUIModel) {
        this.itemsUIModel = itemsUIModel;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_product, parent, false);

        return new ItemsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ItemsViewHolder itemsViewHolder = (ItemsViewHolder) holder;

        for (int i = 0; i < itemsUIModel.size(); i++) {
            if (position == i){
                itemsViewHolder.productName.setText(itemsUIModel.get(i).name);
                itemsViewHolder.productCategory.setText(itemsUIModel.get(i).category);
                itemsViewHolder.productPrice.setText(itemsUIModel.get(i).price);
                itemsViewHolder.productTax.setText(itemsUIModel.get(i).tax);
            }
        }
    }


    @Override
    public int getItemCount() {
        return itemsUIModel.size();
    }

    class ItemsViewHolder extends RecyclerView.ViewHolder {

        public TextView productName;
        public TextView productCategory;
        public TextView productPrice;
        public TextView productTax;

        public ItemsViewHolder(@NonNull View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.nameTextView);
            productCategory = itemView.findViewById(R.id.categoryTextView);
            productPrice = itemView.findViewById(R.id.priceTextView);
            productTax = itemView.findViewById(R.id.taxTextView);

        }
    }
}
