package com.aryotech.shoping;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.aryotech.shoping.Kelas.Product;
import com.bumptech.glide.Glide;

public class DetailProduk extends AppCompatActivity {

    private TextView tvIdProd, tvNmProd, tvSlugProd, tvQty, tvIdmerch, tvNmMerch, tvSlugMerch, tvIdCat, tvNmCat;
    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_produk);

        Product product = getIntent().getParcelableExtra("data");
        inisialisasi();

        Glide.with(this).load(product.getProductImage()).into(img);
        tvIdProd.setText("Id Product        :   "+String.valueOf(product.getProductId()));
        tvNmProd.setText("Name Product      :   "+product.getProductNama());
        tvSlugProd.setText("Slug Product        :   "+product.getProductSlug());
        tvQty.setText("Qty Product      :   "+String.valueOf(product.getProductQty()));
        tvIdmerch.setText("Id Merchant      :   "+String.valueOf(product.getMerchants().getMerchantId()));
        tvNmMerch.setText("Name Merchant        :   "+product.getMerchants().getMerchantName());
        tvSlugMerch.setText("Slug Merchant      :   "+product.getMerchants().getMerchantSLug());
        tvIdCat.setText("Id Category        :   "+String.valueOf(product.getCategories().getCategoryId()));
        tvNmCat.setText("Name Category      :   "+product.getCategories().getCategoryName());
    }

    public void inisialisasi(){
        img = findViewById(R.id.img_detail_prod);
        tvIdProd = findViewById(R.id.tv_detil_id);
        tvNmProd = findViewById(R.id.tv_detail_prod);
        tvSlugProd = findViewById(R.id.tv_detail_slug);
        tvQty = findViewById(R.id.tv_detail_qty);
        tvIdmerch = findViewById(R.id.tv_detail_mid);
        tvNmMerch = findViewById(R.id.tv_detail_mname);
        tvSlugMerch = findViewById(R.id.tv_detail_mslug);
        tvIdCat = findViewById(R.id.tv_detail_cid);
        tvNmCat = findViewById(R.id.tv_detail_cname);
    }
}
