package com.example.laptopmobileapp3.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.laptopmobileapp3.MainActivity;
import com.example.laptopmobileapp3.R;
import com.example.laptopmobileapp3.billing;
import com.example.laptopmobileapp3.login;
import com.example.laptopmobileapp3.utils.adapter.model.CartAdapter;
import com.example.laptopmobileapp3.utils.adapter.model.LaptopmodelCart;
import com.example.laptopmobileapp3.viewmodel.CartViewModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class CartActivity extends AppCompatActivity implements CartAdapter.CartClickedListeners {

    FirebaseAuth auth;
    FirebaseUser user;
    private Button Btn;
    private RecyclerView recyclerView;
    private CartViewModel cartViewModel;
    private TextView textView,totalCartPriceTv, Userdetails;
    private AppCompatButton checkoutBtn;
    private CardView cardView;
    private CartAdapter cartAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        Userdetails = findViewById(R.id.userdetails);
        Btn = findViewById(R.id.loginBtn);

        Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Redirect to the login activity when the Profile button is clicked
                Intent intent = new Intent(CartActivity.this, login.class);
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


        initializeVariables();

        cartViewModel.getAllCartItems().observe(this, new Observer<List<LaptopmodelCart>>() {
            @Override
            public void onChanged(List<LaptopmodelCart> laptopmodelCarts) {
                double price = 0;
                cartAdapter.setLaptopmodelCartList(laptopmodelCarts);

                for (int i=0;i<laptopmodelCarts.size();i++){
                    price = price + laptopmodelCarts.get(i).getTotalItemPrice();
                }
                totalCartPriceTv.setText(String.valueOf(price));
            }
        });

        checkoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CartActivity.this, billing.class);
                startActivity(intent);
            }
        });

    }

    private void initializeVariables() {

        cartAdapter = new CartAdapter(this);
        textView = findViewById(R.id.textView2);
        cardView = findViewById(R.id.cartActivityCardView);
        totalCartPriceTv = findViewById(R.id.cartActivityTotalPriceTv);
        checkoutBtn = findViewById(R.id.cartActivityCheckoutBtn);
        cartViewModel = new ViewModelProvider(this).get(CartViewModel.class);
        recyclerView = findViewById(R.id.cartRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(cartAdapter);
    }


    @Override
    public void onDeleteClicked(LaptopmodelCart laptopmodelCart) {
        cartViewModel.deleteCartItem(laptopmodelCart);
    }

    @Override
    public void onPlusClicked(LaptopmodelCart laptopmodelCart) {
        int quantity = laptopmodelCart.getQuantity() + 1;
        cartViewModel.updateQuantity(laptopmodelCart.getId() , quantity);
        cartViewModel.updatePrice(laptopmodelCart.getId() , quantity*laptopmodelCart.getLaptopPrice());
        cartAdapter.notifyDataSetChanged();
    }

    @Override
    public void onMinusClicked(LaptopmodelCart laptopmodelCart) {

        int quantity = laptopmodelCart.getQuantity() - 1;
        if (quantity != 0){
            cartViewModel.updateQuantity(laptopmodelCart.getId() , quantity);
            cartViewModel.updatePrice(laptopmodelCart.getId() , quantity*laptopmodelCart.getLaptopPrice());
            cartAdapter.notifyDataSetChanged();
        }else{
            cartViewModel.deleteCartItem(laptopmodelCart);
        }
    }
}