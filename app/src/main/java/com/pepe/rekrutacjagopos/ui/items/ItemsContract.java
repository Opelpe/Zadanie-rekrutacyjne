package com.pepe.rekrutacjagopos.ui.items;

import com.pepe.rekrutacjagopos.data.model.ui.ItemModelUI;

import java.util.List;

public interface ItemsContract {

    interface View {
        void setProductsView(List<ItemModelUI> itemModelUI);
    }

    interface Presenter{
        void viewCreated(ItemsContract.View view);
    }
}
