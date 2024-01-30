package com.example.laptopmobileapp3.utils.adapter.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "laptop_table")
public class LaptopmodelCart {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String laptopName , laptopBrandName;
    private int laptopImage;
    private double laptopPrice;
    private int quantity;
    private double totalItemPrice;

    public String getLaptopName() {
        return laptopName;
    }

    public void setLaptopName(String laptopName) {
        this.laptopName = laptopName;
    }

    public String getLaptopBrandName() {
        return laptopBrandName;
    }

    public void setLaptopBrandName(String laptopBrandName) {
        this.laptopBrandName = laptopBrandName;
    }

    public int getLaptopImage() {
        return laptopImage;
    }

    public void setLaptopImage(int laptopImage) {
        this.laptopImage = laptopImage;
    }

    public double getLaptopPrice() {
        return laptopPrice;
    }

    public void setLaptopPrice(double laptopPrice) {
        this.laptopPrice = laptopPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalItemPrice() {
        return totalItemPrice;
    }

    public void setTotalItemPrice(double totalItemPrice) {
        this.totalItemPrice = totalItemPrice;
    }
}
