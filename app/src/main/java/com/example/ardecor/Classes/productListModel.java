package com.example.ardecor.Classes;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;

import androidx.annotation.NonNull;

public class productListModel implements Parcelable {
//    String productListName,productListPrice,productListDescription,productListImage;
     String id,item_name,item_brand,item_category,item_price,item_rating,item_description;
     String imagelink1,imagelink2,imagelink3,imagelink4;
     String item_dimensions1,item_dimensions2,item_dimensions3,item_dimensions4,item_dimensions5,item_dimensions6;

    public productListModel(String id, String item_name, String item_brand, String item_category, String item_price, String item_rating, String item_description, String imagelink1, String imagelink2, String imagelink3, String imagelink4, String item_dimensions1, String item_dimensions2, String item_dimensions3, String item_dimensions4, String item_dimensions5, String item_dimensions6) {
        this.id = id;
        this.item_name = item_name;
        this.item_brand = item_brand;
        this.item_category = item_category;
        this.item_price = item_price;
        this.item_rating = item_rating;
        this.item_description = item_description;
        this.imagelink1 = imagelink1;
        this.imagelink2 = imagelink2;
        this.imagelink3 = imagelink3;
        this.imagelink4 = imagelink4;
        this.item_dimensions1 = item_dimensions1;
        this.item_dimensions2 = item_dimensions2;
        this.item_dimensions3 = item_dimensions3;
        this.item_dimensions4 = item_dimensions4;
        this.item_dimensions5 = item_dimensions5;
        this.item_dimensions6 = item_dimensions6;
    }

//    public productListModel(String productListName, String productListPrice, String productListDescription, String productListImage) {
//        this.productListName = productListName;
//        this.productListPrice = productListPrice;
//        this.productListDescription = productListDescription;
//        this.productListImage = productListImage;
//    }

    protected productListModel(Parcel in) {
        id = in.readString();
        item_name = in.readString();
        item_brand = in.readString();
        item_category = in.readString();
        item_price = in.readString();
        item_rating = in.readString();
        item_description = in.readString();
        imagelink1 = in.readString();
        imagelink2 = in.readString();
        imagelink3 = in.readString();
        imagelink4 = in.readString();
        item_dimensions1 = in.readString();
        item_dimensions2 = in.readString();
        item_dimensions3 = in.readString();
        item_dimensions4 = in.readString();
        item_dimensions5 = in.readString();
        item_dimensions6 = in.readString();
    }

    public static final Creator<productListModel> CREATOR = new Creator<productListModel>() {
        @Override
        public productListModel createFromParcel(Parcel in) {
            return new productListModel(in);
        }

        @Override
        public productListModel[] newArray(int size) {
            return new productListModel[size];
        }
    };



    public String getProductListName() {
        return item_name;
    }

    public String getProductListPrice() {
        return item_price;
    }

    public String getProductListDescription() {
        return "";
    }

    public String getProductListImage() {return imagelink1;}

    public String getId() {
        return id;
    }

    public String getItem_name() {
        return item_name;
    }

    public String getItem_brand() {
        return item_brand;
    }

    public String getItem_category() {
        return item_category;
    }

    public String getItem_price() {
        return item_price;
    }

    public String getItem_rating() {
        return item_rating;
    }

    public String getItem_description() {
        return item_description;
    }

    public String getImagelink1() {
        return imagelink1;
    }

    public String getImagelink2() {
        return imagelink2;
    }

    public String getImagelink3() {
        return imagelink3;
    }

    public String getImagelink4() {
        return imagelink4;
    }

    public String getItem_dimensions1() {
        return item_dimensions1;
    }

    public String getItem_dimensions2() {
        return item_dimensions2;
    }

    public String getItem_dimensions3() {
        return item_dimensions3;
    }

    public String getItem_dimensions4() {
        return item_dimensions4;
    }

    public String getItem_dimensions5() {
        return item_dimensions5;
    }

    public String getItem_dimensions6() {
        return item_dimensions6;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(item_name);
        dest.writeString(item_brand);
        dest.writeString(item_category);
        dest.writeString(item_price);
        dest.writeString(item_rating);
        dest.writeString(item_description);
        dest.writeString(imagelink1);
        dest.writeString(imagelink2);
        dest.writeString(imagelink3);
        dest.writeString(imagelink4);
        dest.writeString(item_dimensions1);
        dest.writeString(item_dimensions2);
        dest.writeString(item_dimensions3);
        dest.writeString(item_dimensions4);
        dest.writeString(item_dimensions5);
        dest.writeString(item_dimensions6);
    }
}
