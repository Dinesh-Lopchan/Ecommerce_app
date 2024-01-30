package com.example.laptopmobileapp3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.laptopmobileapp3.views.DetailedActivity;

public class Purchase extends AppCompatActivity {
    private Button Btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);
        Btn = findViewById(R.id.backBtn);

        Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Redirect to the login activity when the Profile button is clicked
                Intent intent = new Intent(Purchase.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}