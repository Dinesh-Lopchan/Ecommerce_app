package com.example.laptopmobileapp3.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.laptopmobileapp3.dao.CartDAO;
import com.example.laptopmobileapp3.database.CartDatabase;
import com.example.laptopmobileapp3.utils.adapter.model.LaptopmodelCart;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class CartRepo {

    private CartDAO cartDAO;
    private LiveData<List<LaptopmodelCart>> allCartItemsLiveData;
    private Executor executor = Executors.newSingleThreadExecutor();

    public LiveData<List<LaptopmodelCart>> getAllCartItemsLiveData() {
        return allCartItemsLiveData;
    }

    public CartRepo(Application application){
        cartDAO = CartDatabase.getInstance(application).cartDAO();
        allCartItemsLiveData = cartDAO.getAllCartItems();
    }

    public void insertCartItem(LaptopmodelCart laptop){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                cartDAO.insertLaptop(laptop);
            }
        });
    }

    public void deleteCartItem(LaptopmodelCart laptop){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                cartDAO.deleteCartItem(laptop);
            }
        });
    }

    public void updateQuantity(int id, int quantity){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                cartDAO.updateQuantity(id, quantity);
            }
        });
    }

    public void updatePrice(int id, double price){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                cartDAO.updatePrice(id, price);
            }
        });
    }

    public void deleteAllCartItems(){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                cartDAO.deleteAllItems();
            }
        });
    }
}
