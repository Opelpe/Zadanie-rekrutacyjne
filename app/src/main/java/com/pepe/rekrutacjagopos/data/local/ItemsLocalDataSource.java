package com.pepe.rekrutacjagopos.data.local;

import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

@Entity
public class ItemsLocalDataSource {

    @Id long id;

    public List<String> productName;

    @Inject
    public ItemsLocalDataSource() {
    }
}
