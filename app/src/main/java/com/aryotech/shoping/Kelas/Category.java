package com.aryotech.shoping.Kelas;

import android.os.Parcel;
import android.os.Parcelable;

public class Category implements Parcelable {

    private int categoryId;
    private String categoryName;

    public Category (long categoryId, String categoryName){

        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.categoryId);
        dest.writeString(this.categoryName);
    }

    protected Category(Parcel in) {
        this.categoryId = in.readInt();
        this.categoryName = in.readString();
    }

    public static final Parcelable.Creator<Category> CREATOR = new Creator<Category>() {
        @Override
        public Category createFromParcel(Parcel source) {
            return new Category(source);
        }

        @Override
        public Category[] newArray(int size) {
            return new Category[size];
        }
    };
}
