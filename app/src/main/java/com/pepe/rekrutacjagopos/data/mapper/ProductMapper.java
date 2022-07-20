package com.pepe.rekrutacjagopos.data.mapper;

import com.pepe.rekrutacjagopos.data.local.model.LocalItemModel;
import com.pepe.rekrutacjagopos.data.model.ui.ItemModelUI;

import java.util.Locale;

public class ProductMapper {

    public static ItemModelUI mapToUIModel(LocalItemModel itemModel) {
        if (itemModel.imageURL != null) {
            return new ItemModelUI(formatName(itemModel.name), formatCategory(itemModel.category),
                    formatPrice(itemModel.priceAmount, itemModel.currency), formatTax(itemModel.tax), itemModel.imageURL);
        } else {
            return new ItemModelUI(formatName(itemModel.name), formatCategory(itemModel.category),
                    formatPrice(itemModel.priceAmount, itemModel.currency), formatTax(itemModel.tax));
        }
    }

    private static String formatTax(int tax) {
        if (tax == 1) {
            return "23%";
        } else {
            if (tax == 2) {
                return "8%";
            } else {
                return "";
            }
        }
    }

    private static String formatCategory(int category) {
        if (category == 1) {
            return "NAPOJE";
        } else {
            if (category == 2) {
                return "FAST FOOD";
            } else {
                if (category == 3) {
                    return "DESERY";
                } else {
                    return "";
                }
            }
        }
    }

    private static String formatPrice(int amount, String currency) {
        return amount + " " + currency;
    }

    private static String formatName(String name) {
        return name.toUpperCase(Locale.ROOT);
    }
}
