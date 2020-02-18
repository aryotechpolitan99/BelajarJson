package com.aryotech.shoping;

import androidx.appcompat.app.AppCompatActivity;
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

        inisialKomponen();
        generateAdapter();
        getData();
    }

    public void inisialKomponen(){

        recyclerView = findViewById(R.id.rv_home);
    }

    public void generateAdapter(){

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterProduk);
    }

    public void getData(){
        InputStream jsonFile = getResources().openRawResource(R.raw.data);
        AsyncTask asyncTask = new AsyncTask(getApplicationContext(), adapterProduk);
        asyncTask.execute(jsonFile);


    }

}
