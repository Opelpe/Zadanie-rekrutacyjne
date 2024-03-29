package com.pepe.rekrutacjagopos.data.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pepe.rekrutacjagopos.R;
import com.pepe.rekrutacjagopos.data.local.ObjectBox;
import com.pepe.rekrutacjagopos.data.model.ui.ItemModelUI;
import com.squareup.picasso.Picasso;

import java.util.List;

import io.objectbox.Box;

public class ItemsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = ItemsAdapter.class.getSimpleName();

    private List<ItemModelUI> itemModelUI;

    public ItemsAdapter(List<ItemModelUI> itemModelUI) {
        this.itemModelUI = itemModelUI;
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

        for (int i = 0; i < itemModelUI.size(); i++) {

            if (position == i) {

                if (itemModelUI.get(i).defaultImage != null) {
                    Picasso.with(itemsViewHolder.itemView.getContext()).load(itemModelUI.get(i).defaultImage).into(itemsViewHolder.productImage);
                } else {
                    itemsViewHolder.productImage.setImageResource(R.drawable.ic_image_not_supported);
                }
                itemsViewHolder.productName.setText(itemModelUI.get(i).name);
                itemsViewHolder.productCategory.setText(itemModelUI.get(i).category);
                itemsViewHolder.productPrice.setText(itemModelUI.get(i).price);
                itemsViewHolder.productTax.setText(itemModelUI.get(i).tax);
            }
        }
    }

    @Override
    public int getItemCount() {
        return itemModelUI.size();
    }

    class ItemsViewHolder extends RecyclerView.ViewHolder {

        public TextView productName;
        public TextView productCategory;
        public TextView productPrice;
        public TextView productTax;
        public ImageView productImage;

        public ItemsViewHolder(@NonNull View itemView) {
            super(itemView);

            productName = itemView.findViewById(R.id.nameTextView);
            productCategory = itemView.findViewById(R.id.categoryTextView);
            productPrice = itemView.findViewById(R.id.priceTextView);
            productTax = itemView.findViewById(R.id.taxTextView);
            productImage = itemView.findViewById(R.id.itemImageView);
        }
    }
}
