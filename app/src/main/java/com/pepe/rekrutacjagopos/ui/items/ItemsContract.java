package com.pepe.rekrutacjagopos.ui.items;

import com.pepe.rekrutacjagopos.data.model.ui.ItemsUIModel;

import java.util.List;

public interface ItemsContract {

    interface View {
        void setProductsView(List<ItemsUIModel> itemsUIModel);
    }

    interface Presenter{
        void viewCreated(ItemsContract.View view);
    }
}
