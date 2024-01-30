package com.example.laptopmobileapp3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.laptopmobileapp3.utils.adapter.model.LaptopItem;
import com.example.laptopmobileapp3.utils.adapter.model.LaptopItemAdapter;
import com.example.laptopmobileapp3.utils.adapter.model.LaptopmodelCart;
import com.example.laptopmobileapp3.viewmodel.CartViewModel;
import com.example.laptopmobileapp3.views.CartActivity;
import com.example.laptopmobileapp3.views.DetailedActivity;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LaptopItemAdapter.LaptopClickedListeners {

    private ImageButton Profile;
    private RecyclerView recyclerView;
    private List<LaptopItem> LaptopItemList;
    private LaptopItemAdapter adapter;
    private CartViewModel viewModel;
    private List<LaptopmodelCart> laptopmodelCartList;
    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Profile = findViewById(R.id.profile);


        Profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                // Redirect to the login activity when the Profile button is clicked
                Intent intent = new Intent(MainActivity.this, login.class);
                startActivity(intent);
            }
        });

        initializaVaribles();
        setUpList();


        adapter.setLaptopItemList(LaptopItemList);
        recyclerView.setAdapter(adapter);
    }


    @Override
    protected void onResume() {
        super.onResume();
        viewModel.getAllCartItems().observe(this, new Observer<List<LaptopmodelCart>>() {
            @Override
            public void onChanged(List<LaptopmodelCart> laptopmodelCarts) {
                laptopmodelCartList.addAll(laptopmodelCarts);
            }
        });
    }

    private void setUpList(){
        LaptopItemList.add(new LaptopItem("Hp laptop","HP", R.drawable.hp, 800));
        LaptopItemList.add(new LaptopItem("Asus laptop","Asus", R.drawable.asus, 810));
        LaptopItemList.add(new LaptopItem("Lenovo laptop","Lenovo", R.drawable.lenovo, 790));
        LaptopItemList.add(new LaptopItem("Dell laptop","Dell", R.drawable.dell, 850));
        LaptopItemList.add(new LaptopItem("Microsoft laptop","Microsoft", R.drawable.microsoft, 890));
    }


    private void initializaVaribles(){


        linearLayout = findViewById(R.id.LinearLayout);

        laptopmodelCartList = new ArrayList<>();

        viewModel = new ViewModelProvider(this).get(CartViewModel.class);

        LaptopItemList = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));

        adapter = new LaptopItemAdapter(this);
    }


    @Override
    public void onCardClicked(LaptopItem laptop) {

        Intent intent = new Intent(MainActivity.this, DetailedActivity.class);
        intent.putExtra("laptopItem", laptop);
        startActivity(intent);
    }

    @Override
    public void onAddToCartBtnClicked(LaptopItem laptopItem) {
        LaptopmodelCart laptopmodelCart = new LaptopmodelCart();
        laptopmodelCart.setLaptopName(laptopItem.getLaptopName());
        laptopmodelCart.setLaptopBrandName(laptopItem.getLaptopBrandName());
        laptopmodelCart.setLaptopPrice(laptopItem.getLaptopPrice());
        laptopmodelCart.setLaptopImage(laptopItem.getLaptopImage());

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

        makeSnackBar("Item Added to the Cart");
    }

    private void makeSnackBar(String mesge){
        Snackbar.make(linearLayout, mesge, Snackbar.LENGTH_SHORT)
                .setAction("Go to the Cart", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(MainActivity.this, CartActivity.class));
                    }
                }).show();
    }

}