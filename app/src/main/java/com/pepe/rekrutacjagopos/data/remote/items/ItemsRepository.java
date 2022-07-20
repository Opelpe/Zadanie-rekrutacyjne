package com.pepe.rekrutacjagopos.data.remote.items;

import android.util.Log;

import com.pepe.rekrutacjagopos.data.local.LocalDataSource;
import com.pepe.rekrutacjagopos.data.local.ObjectBox;
import com.pepe.rekrutacjagopos.data.local.model.LocalItemModel;
import com.pepe.rekrutacjagopos.data.mapper.ProductMapper;
import com.pepe.rekrutacjagopos.data.model.ui.ItemModelUI;
import com.pepe.rekrutacjagopos.data.remote.model.item.ItemRetrofitModel;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import io.objectbox.Box;

public class ItemsRepository {

    private final LocalDataSource localDataSource;
    private final ItemsRemoteDataSource itemsRemoteDataSource;
    private ProductListener productListener;

    private static final String TAG = ItemsRepository.class.getSimpleName();


    private ItemsRemoteDataSource.DataListener dataListener = new ItemsRemoteDataSource.DataListener() {
        @Override
        public void onResponse(List<ItemRetrofitModel> retrofitResponse) {
            Log.d(TAG, "REMOTE list, SIZE: " + retrofitResponse.size());

                localDataSource.setLocalItems(retrofitResponse);

        }
    };

    private LocalDataSource.ItemsListener itemsListener = () -> {
        Box<LocalItemModel> localBox = ObjectBox.get().boxFor(LocalItemModel.class);
        List<LocalItemModel> boxList = localBox.getAll();

        setUIModel(boxList);
    };

    public interface ProductListener {
        void onItemsLoaded(List<ItemModelUI> items);
    }

    public void setUIItemsListener(ProductListener listener) {
        productListener = listener;
    }


    @Inject
    public ItemsRepository(LocalDataSource localDataSource, ItemsRemoteDataSource itemsRemoteDataSource) {
        this.localDataSource = localDataSource;
        this.itemsRemoteDataSource = itemsRemoteDataSource;

    }

    public void getItems() {
        Log.d("getItems REPO", "get items started");
        localDataSource.setProductsListener(itemsListener);
        itemsListener.localDataLoaded();
        itemsRemoteDataSource.setDataListener(dataListener);
        itemsRemoteDataSource.getRemoteItems();

    }

    private void setUIModel(List<LocalItemModel> itemModel) {
        Box<ItemModelUI> uiModelBox = ObjectBox.get().boxFor(ItemModelUI.class);
        List<ItemModelUI> uiList = uiModelBox.getAll();

        Log.d(TAG, "ITEMS REPOSITORY FORMAT DATA: " + itemModel.size());

        List<ItemModelUI> itemModelUIList = itemModel.stream().map(ProductMapper::mapToUIModel).collect(Collectors.toList());

        if (!uiList.isEmpty()) {
            if (itemModelUIList.size() != uiList.size()) {
                uiModelBox.removeAll();
                uiModelBox.put(itemModelUIList);
            }
        } else {
            uiModelBox.put(itemModelUIList);
        }
        productListener.onItemsLoaded(uiList);
    }
}
