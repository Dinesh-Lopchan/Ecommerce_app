package com.example.laptopmobileapp3.utils.adapter.model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.laptopmobileapp3.R;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private CartClickedListeners cartClickedListeners;
    private List<LaptopmodelCart> laptopmodelCartList;
    public CartAdapter(CartClickedListeners cartClickedListeners){
        this.cartClickedListeners = cartClickedListeners;
    }
    public void setLaptopmodelCartList(List<LaptopmodelCart> laptopmodelCartList){
        this.laptopmodelCartList = laptopmodelCartList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.each_cart_item, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        LaptopmodelCart laptopmodelCart = laptopmodelCartList.get(position);
        holder.laptopImageView.setImageResource(laptopmodelCart.getLaptopImage());
        holder.laptopNameTv.setText(laptopmodelCart.getLaptopName());
        holder.laptopBrandNameTv.setText(laptopmodelCart.getLaptopBrandName());
        holder.laptopQuantity.setText(laptopmodelCart.getQuantity()+ "");
        holder.laptopPriceTv.setText(laptopmodelCart.getTotalItemPrice() + "");

        holder.deleteShoeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cartClickedListeners.onDeleteClicked(laptopmodelCart);
            }
        });

        holder.addQuantityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cartClickedListeners.onPlusClicked(laptopmodelCart);
            }
        });

        holder.minusQuantityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cartClickedListeners.onMinusClicked(laptopmodelCart);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (laptopmodelCartList == null){
            return 0;
        }else{
            return laptopmodelCartList.size();
        }
    }

    public class CartViewHolder extends RecyclerView.ViewHolder{

        private TextView laptopNameTv, laptopBrandNameTv, laptopPriceTv, laptopQuantity;
        private ImageView deleteShoeBtn;
        private ImageView laptopImageView;
        private ImageButton addQuantityBtn, minusQuantityBtn;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);

            laptopNameTv = itemView.findViewById(R.id.eachCartItemName);
            laptopBrandNameTv = itemView.findViewById(R.id.eachCartItemBrandNameTv);
            laptopPriceTv = itemView.findViewById(R.id.eachCartItemPriceTv);
            deleteShoeBtn = itemView.findViewById(R.id.eachCartItemDeleteBtn);
            laptopImageView = itemView.findViewById(R.id.eachCartItemIV);
            laptopQuantity = itemView.findViewById(R.id.eachCartItemQuantityTV);
            addQuantityBtn = itemView.findViewById(R.id.eachCartItemAddQuantityBtn);
            minusQuantityBtn = itemView.findViewById(R.id.eachCartItemMinusQuantityBtn);
        }
    }

    public interface CartClickedListeners{
        void onDeleteClicked(LaptopmodelCart laptopmodelCart);
        void onPlusClicked(LaptopmodelCart laptopmodelCart);
        void onMinusClicked(LaptopmodelCart laptopmodelCart);
    }
}
