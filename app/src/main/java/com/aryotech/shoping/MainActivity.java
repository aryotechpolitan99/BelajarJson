package com.aryotech.shoping;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.aryotech.shoping.Adapter.AdapterProduk;

import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    AdapterProduk adapterProduk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rv_home);
        adapterProduk = new AdapterProduk(this);

        recyclerView.setAdapter(adapterProduk);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        String url = "http://192.168.6.221:81/api/products";
        new AsyncTask(this,adapterProduk).execute(url);
    }



}
