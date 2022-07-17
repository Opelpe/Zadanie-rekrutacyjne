package com.pepe.rekrutacjagopos.data.local;

import android.util.Log;

import com.pepe.rekrutacjagopos.data.comparators.CategorySorter;
import com.pepe.rekrutacjagopos.data.comparators.NameSorter;
import com.pepe.rekrutacjagopos.data.comparators.PriceAmountSorter;
import com.pepe.rekrutacjagopos.data.local.model.LocalItemModel;
import com.pepe.rekrutacjagopos.data.model.ui.ItemsUIModel;
import com.pepe.rekrutacjagopos.data.remote.items.ItemsRemoteDataSource;
import com.pepe.rekrutacjagopos.data.remote.items.ItemsRepository;
import com.pepe.rekrutacjagopos.data.remote.model.item.ItemRetrofitModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import io.objectbox.Box;


public class LocalDataSource {

    private static final String TAG = LocalDataSource.class.getSimpleName();

    private ProductsListener productsListener;

    public interface ProductsListener {
        void onItemsLoaded(List<LocalItemModel> itemsList);
    }

    public void setItemsUIModel() {
        Box<LocalItemModel> localModelBox = ObjectBox.get().boxFor(LocalItemModel.class);
        Log.d(TAG, "Local DATA Source FORMAT DATA: " + localModelBox.getAll().size());

//        formatData(localModelBox.getAll());
    }

    public void setProductsListener(ProductsListener listener) {
        this.productsListener = listener;
    }

    private ItemsRemoteDataSource.DataListener dataListener = new ItemsRemoteDataSource.DataListener() {
        @Override
        public void onItemsLoaded(List<ItemRetrofitModel> retrofitResponse) {
            Box<LocalItemModel> localBox = ObjectBox.get().boxFor(LocalItemModel.class);
            List<LocalItemModel> itemsList = localBox.getAll();

            if (retrofitResponse != null) {

                retrofitResponse.sort(new CategorySorter()
                        .thenComparing(new PriceAmountSorter())
                        .thenComparing(new NameSorter()));

                List<LocalItemModel> localModelList = new ArrayList<>();
                for (int i = 0; i < retrofitResponse.size(); i++) {

                    LocalItemModel localItemModel;

//                        Log.d(TAG, "Items repository,BEFORE  RETROFIT != LOCAL: " + storedItemList.size() + "\n WWW: " + retrofitResponse.size());
//                        Log.d(TAG, "Local DATA repository LIST EQAULS: " + storedItemList.size() + "\n ReTROFIT: " + localModelList.size());

                    if (retrofitResponse.get(i).imageModel != null) {
//                        if (!itemsList.isEmpty() || itemsList.size() != 0) {
//                            if (!retrofitResponse.get(i).name.equals(itemsList.get(i).name)
//                                    || retrofitResponse.get(i).price.amount != itemsList.get(i).priceAmount
//                                    || retrofitResponse.get(i).category != itemsList.get(i).category
//                                    || retrofitResponse.get(i).tax != itemsList.get(i).tax
//                                    || !retrofitResponse.get(i).price.currency.equals(itemsList.get(i).currency)
//                                    || !retrofitResponse.get(i).imageModel.defaultImage.equals(itemsList.get(i).imageURL)) {
//                                updateLDB(localModelList.get(i), i);
//                            }
//                        } else {
                            localItemModel = new LocalItemModel(retrofitResponse.get(i).name, retrofitResponse.get(i).category, retrofitResponse.get(i).price.amount,
                                    retrofitResponse.get(i).price.currency, retrofitResponse.get(i).tax, retrofitResponse.get(i).imageModel.defaultImage);

                            localModelList.add(localItemModel);

                    } else {
                            localItemModel = new LocalItemModel(retrofitResponse.get(i).name, retrofitResponse.get(i).category, retrofitResponse.get(i).price.amount,
                                    retrofitResponse.get(i).price.currency, retrofitResponse.get(i).tax);

                            localModelList.add(localItemModel);
                        }
                }
                productsListener.onItemsLoaded(localModelList);


            }
        }
    };

    private void updateLDB(LocalItemModel localItemModel, int i) {
        Log.d(TAG, "Items repository, RETROFIT != LOCAL: " + "\n WWW: " + localItemModel.name);
        Box<LocalItemModel> box = ObjectBox.get().boxFor(LocalItemModel.class);
        Log.d(TAG, "update Data Base something not equal");
        box.getAll().get(i).updateName(localItemModel.name);
        box.getAll().get(i).updatePriceAmount(localItemModel.priceAmount);
        box.getAll().get(i).updateCategory(localItemModel.category);
        box.getAll().get(i).updateCurrency(localItemModel.currency);
        box.getAll().get(i).updateImageURL(localItemModel.imageURL);
        box.getAll().get(i).updateTax(localItemModel.tax);
    }

    @Inject
    public LocalDataSource() {
        ItemsRemoteDataSource.setItemsListener(dataListener);
    }


}
