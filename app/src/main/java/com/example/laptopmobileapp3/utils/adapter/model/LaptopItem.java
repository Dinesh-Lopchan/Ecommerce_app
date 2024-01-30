package com.example.laptopmobileapp3.utils.adapter.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class LaptopItem implements Parcelable {

    private String laptopName , laptopBrandName;
    private int laptopImage;
    private double laptopPrice;

    public LaptopItem(String laptopName, String laptopBrandName, int laptopImage, double laptopPrice) {
        this.laptopName = laptopName;
        this.laptopBrandName = laptopBrandName;
        this.laptopImage = laptopImage;
        this.laptopPrice = laptopPrice;
    }

    protected LaptopItem(Parcel in) {
        laptopName = in.readString();
        laptopBrandName = in.readString();
        laptopImage = in.readInt();
        laptopPrice = in.readDouble();
    }

    public static final Creator<LaptopItem> CREATOR = new Creator<LaptopItem>() {
        @Override
        public LaptopItem createFromParcel(Parcel in) {
            return new LaptopItem(in);
        }

        @Override
        public LaptopItem[] newArray(int size) {
            return new LaptopItem[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(laptopName);
        parcel.writeString(laptopBrandName);
        parcel.writeInt(laptopImage);
        parcel.writeDouble(laptopPrice);
    }
}
