package com.example.laptopmobileapp3.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.laptopmobileapp3.utils.adapter.model.LaptopmodelCart;

import java.util.List;

@Dao
public interface CartDAO {

    @Insert
    void insertLaptop(LaptopmodelCart laptop);

    @Query("SELECT * FROM laptop_table")
    LiveData<List<LaptopmodelCart>> getAllCartItems();

    @Delete
    void deleteCartItem(LaptopmodelCart laptop);

    @Query("UPDATE laptop_table SET quantity=:quantity WHERE id=:id")
    void updateQuantity(int id, int quantity);

    @Query("UPDATE laptop_table SET totalItemPrice=:totalItemPrice WHERE id=:id")
    void updatePrice(int id, double totalItemPrice);

    @Query("DELETE FROM laptop_table")
    void deleteAllItems();
}
