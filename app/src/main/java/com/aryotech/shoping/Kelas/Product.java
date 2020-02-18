package com.aryotech.shoping.Kelas;

import android.os.Parcel;
import android.os.Parcelable;

public class Product implements Parcelable {
    private int productId;
    private String productNama;
    private String productSlug;
    private int productQty;
    private String productImage;
    private Merchant merchants;
    private Category categories;

    public Product (long productId, String productNama, String productSlug, String productQty, int productImage, Merchant merchants, Category categories){

        this.productId = productId;
        this.productNama = productNama;
        this.productSlug = productSlug;
        this.productQty = productQty;
        this.productImage = productImage;
        this.merchants = merchants;
        this.categories = categories;
    }

    public int getProductId() {
        return productId;
    }

    public String getProductNama() {
        return productNama;
    }

    public String getProductSlug() {
        return productSlug;
    }

    public int getProductQty() {
        return productQty;
    }

    public String getProductImage() {
        return productImage;
    }

    public Merchant getMerchants() {
        return merchants;
    }

    public Category getCategories() {
        return categories;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.productId);
        dest.writeString(this.productNama);
        dest.writeString(this.productSlug);
        dest.writeInt(this.productQty);
        dest.writeString(this.productImage);
        dest.writeParcelable(this.merchants, flags);
        dest.writeParcelable(this.categories, flags);
    }

    protected Product(Parcel in) {
        this.productId = in.readInt();
        this.productNama = in.readString();
        this.productSlug = in.readString();
        this.productQty = in.readInt();
        this.productImage = in.readString();
        this.merchants = in.readParcelable(Merchant.class.getClassLoader());
        this.categories = in.readParcelable(Category.class.getClassLoader());
    }

    public static final Parcelable.Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel source) {
            return new Product(source);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };
}
