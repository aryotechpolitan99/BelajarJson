package com.aryotech.shoping.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aryotech.shoping.DetailProduk;
import com.aryotech.shoping.Kelas.Product;
import com.aryotech.shoping.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class AdapterProduk extends RecyclerView.Adapter<AdapterProduk.MyViewHolder> {

    private Context context;
    private ArrayList<Product> products = new ArrayList<>();

    public AdapterProduk(Context context){
        this.context = context;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list_product, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        holder.tvName.setText(products.get(position).getProductNama());
        holder.tvMerch.setText(products.get(position).getMerchants().getMerchantName());

        String baseUrl = "http://192.168.6.221:81/storage/";
        String url = baseUrl+products.get(position).getProductImage();
        Glide.with(context)
                .load(url)
                .into(holder.ivProductImage);
        holder.iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, DetailProduk.class);
                intent.putExtra("data", products.get(position));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{

        public ImageView ivProductImage;
        private TextView tvName, tvMerch;
        private ImageView iv;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tv_nm_prod);
            iv = itemView.findViewById(R.id.img_list);
            tvMerch = itemView.findViewById(R.id.tv2_merch);
            ivProductImage = itemView.findViewById(R.id.img_list);
        }
    }

    public void addData(ArrayList<Product> products){
        this.products = products;
    }
}
