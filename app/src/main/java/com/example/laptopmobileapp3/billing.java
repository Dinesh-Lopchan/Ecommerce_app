package com.example.laptopmobileapp3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.laptopmobileapp3.views.CartActivity;
import com.google.firebase.auth.FirebaseAuth;


public class billing extends AppCompatActivity {

    Button Checkout;
    ImageButton Home, Cart, Profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_billing);

        Checkout = findViewById(R.id.checkout);
        Home = findViewById(R.id.home);
        Cart = findViewById(R.id.cart);
        Profile = findViewById(R.id.profile);

        Checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Redirect login screen when logout button is clicked
                Intent intent = new Intent(billing.this, Purchase.class);
                startActivity(intent);
            }
        });

        Profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), login.class);
                startActivity(intent);
                finish();
            }

        });

        Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Redirect login screen when logout button is clicked
                Intent intent = new Intent(billing.this, MainActivity.class);
                startActivity(intent);
            }
        });


        Cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Redirect login screen when logout button is clicked
                Intent intent = new Intent(billing.this, CartActivity.class);
                startActivity(intent);
            }
        });
    }
}

