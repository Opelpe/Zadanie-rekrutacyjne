package com.pepe.rekrutacjagopos.data.local;

import android.content.Context;
import android.mtp.MtpObjectInfo;


import com.pepe.rekrutacjagopos.data.MyObjectBox;

import io.objectbox.BoxStore;

public class ObjectBox {

    private static BoxStore boxStore;

    public static void init(Context context) {

        boxStore = MyObjectBox.builder()
                .androidContext(context.getApplicationContext())
                .build();
    }

    public static BoxStore get() {
        return boxStore;
    }

}
