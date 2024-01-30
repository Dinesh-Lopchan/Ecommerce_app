package com.example.laptopmobileapp3.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.laptopmobileapp3.dao.CartDAO;
import com.example.laptopmobileapp3.utils.adapter.model.LaptopmodelCart;

@Database(entities = {LaptopmodelCart.class}, version = 1)
public abstract class CartDatabase extends RoomDatabase {

    public abstract CartDAO cartDAO();
    private static CartDatabase instance;

    public static synchronized CartDatabase getInstance(Context context) {

        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext()
                            , CartDatabase.class, "LaptopDatabase")
                    .fallbackToDestructiveMigration().build();
        }
        return instance;
    }
}

