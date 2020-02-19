package com.aryotech.shoping;

import android.content.Context;

import com.aryotech.shoping.Adapter.AdapterProduk;
import com.aryotech.shoping.Kelas.Category;
import com.aryotech.shoping.Kelas.Merchant;
import com.aryotech.shoping.Kelas.Product;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class AsyncTask  extends android.os.AsyncTask<String, Void, ArrayList<Product>> {

    WeakReference<AdapterProduk> adapterProduk;
    Context context;

    public AsyncTask(Context context, AdapterProduk adapterProduk){

        this.context = context;
        this.adapterProduk = new WeakReference<>(adapterProduk);
    }

    @Override
    protected ArrayList<Product> doInBackground(String... url) {

        ArrayList<Product> produk = new ArrayList<>();
        String json = loadJsonFromApi(url[0]);
        produk = deserializeJSON(json);

        return produk;
    }

    @Override
    protected void onPostExecute(ArrayList<Product> products) {

        AdapterProduk adapterProduk1 = adapterProduk.get();
        adapterProduk1.addData(products);
        adapterProduk1.notifyDataSetChanged();
    }

    private String loadJsonFromApi(String urlParam){
        String json = null;
        //Network Calls
        try {
            HttpURLConnection connection = null;
            URL url = new URL(urlParam); //urlParam ex: http://www.google.com
            try{
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                InputStream is = connection.getInputStream(); // obj input dari stream file
                InputStreamReader isr = new InputStreamReader(is); // obj input untuk string/char

                BufferedReader reader = new BufferedReader(isr); //obj reader untuk inputan

                StringBuffer sb = new StringBuffer(); //empty string buffer
                String eachLine;
                while ((eachLine = reader.readLine()) != null){
                    sb.append(eachLine); // fill the string buffer with each line from reader
                }

                json = sb.toString(); // convert sb to string and assign to json.


            }catch (Exception e){
                e.printStackTrace();
            }
            finally {
                if(connection != null){
                    connection.disconnect();
                }
            }
        }
        catch (MalformedURLException e){
            e.printStackTrace();
            return e.getMessage();
        }
        return json;
    }

    private ArrayList<Product> deserializeJSON(String jsonParam){
        ArrayList<Product> products = new ArrayList<>();
        String json = jsonParam;

        try {
            JSONObject jsonData = new JSONObject(json);
            JSONArray data = jsonData.getJSONArray("data");
            for (int i=0;i < data.length();i++){
                Product productObj;
                JSONObject product = data.getJSONObject(i);
                long productId = product.getLong("productId");
                String productName = product.getString("productName");
                String productSlug = product.getString("productSlug");
                String productImage = product.getString("productImage");
                long productQty = product.getLong("productQty");
                JSONObject merchant = product.getJSONObject("merchant");
                JSONObject category = product.getJSONObject("category");

                long categoryId = category.getLong("categoryId");
                String categoryName = category.getString("categoryName");

                Category categoryObj = new Category(categoryId,categoryName);

                long merchantId = merchant.getLong("merchantId");
                String merchantName = merchant.getString("merchantName");
                String merchantSlug = merchant.getString("merchantSlug");

                Merchant merchantObj = new Merchant(merchantId,merchantName,merchantSlug);

                productObj = new Product(productId,productName,productSlug,productQty,productImage,merchantObj,categoryObj);
                products.add(productObj);
            }


        }
        catch (JSONException err){
            err.printStackTrace();

        }
        return products;
    }
}
