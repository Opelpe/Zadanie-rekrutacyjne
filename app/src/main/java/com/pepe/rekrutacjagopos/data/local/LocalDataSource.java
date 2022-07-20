package com.pepe.rekrutacjagopos.data.local;

import android.util.Log;

import com.pepe.rekrutacjagopos.data.mapper.RemoteItemsMapper;
import com.pepe.rekrutacjagopos.data.remote.comparators.CategorySorter;
import com.pepe.rekrutacjagopos.data.remote.comparators.NameSorter;
import com.pepe.rekrutacjagopos.data.remote.comparators.PriceAmountSorter;
import com.pepe.rekrutacjagopos.data.local.model.LocalItemModel;
import com.pepe.rekrutacjagopos.data.remote.model.item.ItemRetrofitModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import io.objectbox.Box;


public class LocalDataSource {

    private static final String TAG = LocalDataSource.class.getSimpleName();

    private ItemsListener itemsListener;

    public void setLocalItems(List<ItemRetrofitModel> retrofitResponse) {
        Log.d(TAG, " onItemsLoaded(List<ItemRetrofitModel> retrofitResponse: START! SIZE: " + retrofitResponse.size());

        Box<LocalItemModel> localBox = ObjectBox.get().boxFor(LocalItemModel.class);
        List<LocalItemModel> boxList = localBox.getAll();

        retrofitResponse.sort(new CategorySorter()
                .thenComparing(new PriceAmountSorter())
                .thenComparing(new NameSorter()));

        List<LocalItemModel> localModelList = new ArrayList<>();
//        List<LocalItemModel> localModelList = getRetrofitResponse(retrofitResponse).stream().map(RemoteItemsMapper::mapToLocalItem).collect(Collectors.toList());

        for (int i = 0; i < retrofitResponse.size(); i++) {

            boolean haveImage = retrofitResponse.get(i).imageModel != null;
            LocalItemModel localItemModel = RemoteItemsMapper.mapToLocalItem(retrofitResponse.get(i), haveImage);
            localModelList.add(localItemModel);
        }

        if (!boxList.isEmpty()) {
            if (localModelList.size() != boxList.size()) {
                localBox.removeAll();
                localBox.put(localModelList);
            }
        } else {
            localBox.put(localModelList);
        }
        itemsListener.localDataLoaded();
    }


    public interface ItemsListener {
        void localDataLoaded();
    }

    public void setProductsListener(ItemsListener listener) {
        this.itemsListener = listener;
    }

    @Inject
    public LocalDataSource() {

    }
}
