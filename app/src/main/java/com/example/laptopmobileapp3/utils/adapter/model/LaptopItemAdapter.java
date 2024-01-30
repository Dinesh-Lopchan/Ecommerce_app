package com.example.laptopmobileapp3.utils.adapter.model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.laptopmobileapp3.R;

import java.util.List;

public class LaptopItemAdapter extends RecyclerView.Adapter<LaptopItemAdapter.LaptopItemViewHolder>{

    private List<LaptopItem> LaptopItemList;
    private LaptopClickedListeners laptopClickedListeners;
    public LaptopItemAdapter(LaptopClickedListeners laptopClickedListeners){
        this.laptopClickedListeners = laptopClickedListeners;
    }
    public void setLaptopItemList(List<LaptopItem> LaptopItemList){
        this.LaptopItemList = LaptopItemList;
    }

    @NonNull
    @Override
    public LaptopItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.each_laptop, parent, false);
        return new LaptopItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LaptopItemViewHolder holder, int position) {
        LaptopItem laptopItem = LaptopItemList.get(position);
        holder.laptopNameTv.setText(laptopItem.getLaptopName());
        holder.laptopBrandNameTv.setText(laptopItem.getLaptopBrandName());
        holder.laptopPriceTv.setText(String.valueOf(laptopItem.getLaptopPrice()));
        holder.laptopImageView.setImageResource(laptopItem.getLaptopImage());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                laptopClickedListeners.onCardClicked(laptopItem);
            }
        });

        holder.addToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                laptopClickedListeners.onAddToCartBtnClicked(laptopItem);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (LaptopItemList == null){
            return 0;
        }else{
            return LaptopItemList.size();
        }
    }

    public class LaptopItemViewHolder extends RecyclerView.ViewHolder{
        private ImageView laptopImageView, addToCartBtn;
        private TextView laptopNameTv, laptopBrandNameTv, laptopPriceTv;
        private CardView cardView;

        public LaptopItemViewHolder(@NonNull View itemView) {
            super(itemView);

            cardView = itemView.findViewById(R.id.eachLaptopCardView);
            addToCartBtn = itemView.findViewById(R.id.eachLaptopAddToCartBtn);
            laptopNameTv = itemView.findViewById(R.id.eachLaptopName);
            laptopImageView = itemView.findViewById(R.id.eachLaptopTv);
            laptopBrandNameTv = itemView.findViewById(R.id.eachLaptopBrandNameTv);
            laptopPriceTv = itemView.findViewById(R.id.eachlaptopPriceTv);

        }
    }

    public interface LaptopClickedListeners{
        void onCardClicked(LaptopItem laptop);
        void onAddToCartBtnClicked(LaptopItem laptopItem);
    }
}
