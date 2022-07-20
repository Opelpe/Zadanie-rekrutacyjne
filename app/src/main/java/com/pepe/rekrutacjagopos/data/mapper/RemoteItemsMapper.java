package com.pepe.rekrutacjagopos.data.mapper;

import com.pepe.rekrutacjagopos.data.local.model.LocalItemModel;
import com.pepe.rekrutacjagopos.data.remote.model.item.ItemRetrofitModel;

public class RemoteItemsMapper {

    public static LocalItemModel mapToLocalItem(ItemRetrofitModel remoteModel, boolean haveImage){
        if (haveImage) {
            return new LocalItemModel(remoteModel.name, remoteModel.category, remoteModel.price.amount, remoteModel.price.currency,
                    remoteModel.tax, remoteModel.imageModel.defaultImage);
        } else {
            return new LocalItemModel(remoteModel.name, remoteModel.category, remoteModel.price.amount, remoteModel.price.currency,
                    remoteModel.tax);
        }
    }
}
