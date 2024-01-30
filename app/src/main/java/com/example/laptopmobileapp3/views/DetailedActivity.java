package com.example.laptopmobileapp3.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.laptopmobileapp3.MainActivity;
import com.example.laptopmobileapp3.R;
import com.example.laptopmobileapp3.login;
import com.example.laptopmobileapp3.utils.adapter.model.LaptopItem;
import com.example.laptopmobileapp3.utils.adapter.model.LaptopmodelCart;
import com.example.laptopmobileapp3.viewmodel.CartViewModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class DetailedActivity extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseUser user;
    private Button Btn;
    private ImageView laptopImageView;
    private TextView Userdetails,laptopNameTv, laptopBrandNameTv, laptopPriceTv;
    private AppCompatButton addToCartBtn;
    private LaptopItem laptop;
    private CartViewModel viewModel;
    private List<LaptopmodelCart> laptopmodelCartList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        Userdetails = findViewById(R.id.userdetails);
        Btn = findViewById(R.id.backBtn);

        Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Redirect to the login activity when the Profile button is clicked
                Intent intent = new Intent(DetailedActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });


        if (user == null){
            Intent intent = new Intent(getApplicationContext(), login.class);
            startActivity(intent);
            finish();
        }else {
            Userdetails.setText(user.getEmail());
        }


        laptop = getIntent().getParcelableExtra("laptopItem");
        initializeVariables();

        viewModel.getAllCartItems().observe(this, new Observer<List<LaptopmodelCart>>() {
            @Override
            public void onChanged(List<LaptopmodelCart> laptopmodelCarts) {
                laptopmodelCartList.addAll(laptopmodelCarts);
            }
        });

        if (laptop != null){
            setDataToWidgets();
        }

        addToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertToRoom();
            }
        });
    }

    private void insertToRoom(){
        LaptopmodelCart laptopmodelCart = new LaptopmodelCart();
        laptopmodelCart.setLaptopName(laptop.getLaptopName());
        laptopmodelCart.setLaptopBrandName(laptop.getLaptopBrandName());
        laptopmodelCart.setLaptopPrice(laptop.getLaptopPrice());
        laptopmodelCart.setLaptopImage(laptop.getLaptopImage());

        final int[] quantity = {1};
        final int[] id = new int[1];

        if (!laptopmodelCartList.isEmpty()){

            for(int i =0; i<laptopmodelCartList.size();i++){
                if (laptopmodelCart.getLaptopName().equals(laptopmodelCartList.get(i).getLaptopName())){
                    quantity[0] = laptopmodelCartList.get(i).getQuantity();
                    quantity[0]++;
                    id[0] = laptopmodelCartList.get(i).getId();
                }
            }
        }

        if (quantity[0]==1){
            laptopmodelCart.setQuantity(quantity[0]);
            laptopmodelCart.setTotalItemPrice(quantity[0]*laptopmodelCart.getLaptopPrice());
            viewModel.insertCartItem(laptopmodelCart);
        }else{

            viewModel.updateQuantity(id[0],quantity[0]);
            viewModel.updatePrice(id[0], quantity[0]*laptopmodelCart.getLaptopPrice());
        }

        startActivity(new Intent(DetailedActivity.this, CartActivity.class));
    }

    private void setDataToWidgets(){
        laptopNameTv.setText(laptop.getLaptopName());
        laptopBrandNameTv.setText(laptop.getLaptopBrandName());
        laptopPriceTv.setText(String.valueOf(laptop.getLaptopPrice()));
        laptopImageView.setImageResource(laptop.getLaptopImage());
    }

    private void initializeVariables(){

        laptopmodelCartList = new ArrayList<>();

        laptopImageView = findViewById(R.id.detailActivityLaptopTv);
        laptopNameTv = findViewById(R.id.detailActivityLaptopNameTv);
        laptopBrandNameTv = findViewById(R.id.detailActivityLaptopBrandNameTv);
        laptopPriceTv = findViewById(R.id.detailActivityLaptopPriceTv);
        addToCartBtn = findViewById(R.id.detailActivityAddToCartBtn);

        viewModel = new ViewModelProvider(this).get(CartViewModel.class);

    }
}