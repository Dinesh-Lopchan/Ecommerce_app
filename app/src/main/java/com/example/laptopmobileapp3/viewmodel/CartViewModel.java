package com.example.laptopmobileapp3.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.laptopmobileapp3.repository.CartRepo;
import com.example.laptopmobileapp3.utils.adapter.model.LaptopmodelCart;

import java.util.List;

public class CartViewModel extends AndroidViewModel {

    private CartRepo cartRepo;

    public CartViewModel(@NonNull Application application) {
        super(application);
        cartRepo = new CartRepo(application);
    }

    public LiveData<List<LaptopmodelCart>> getAllCartItems(){
        return cartRepo.getAllCartItemsLiveData();
    }

    public void insertCartItem(LaptopmodelCart laptop){
        cartRepo.insertCartItem(laptop);
    }

    public void updateQuantity(int id, int quantity){
        cartRepo.updateQuantity(id, quantity);
    }

    public void updatePrice(int id, double price){
        cartRepo.updatePrice(id, price);
    }

    public void deleteCartItem(LaptopmodelCart laptop){
        cartRepo.deleteCartItem(laptop);
    }

    public void deleteAllCartItems(){
        cartRepo.deleteAllCartItems();
    }
}
