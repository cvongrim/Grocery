package com.colinv.grocery;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * GroceryItem Parcelable Class
 * Created by colinv on 11/18/13.
 */
public class GroceryItem implements Parcelable {

    private Long id;
    private String name;
    private String quantity;
    private Boolean is_checked;

    // No-arg Ctor
    public GroceryItem(){}

    /** Getters */
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getQuantity() {
        return quantity;
    }

    public Boolean getIsChecked() {
        return is_checked;
    }

    /** Setters */

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public void setIsChecked(Boolean is_checked) {
        this.is_checked = is_checked;
    }


    /** Used to give additional hints on how to process the received parcel.*/
    @Override
    public int describeContents() {
        // ignore for now
        return 0;
    }

    @Override
    public void writeToParcel(Parcel pc, int flags) {
        pc.writeLong(id);
        pc.writeString(name);
        pc.writeString(quantity);
        pc.writeInt( is_checked ? 1 :0 );
    }

    /** Static field used to regenerate object, individually or as arrays */
    public static final Parcelable.Creator<GroceryItem> CREATOR = new Parcelable.Creator<GroceryItem>() {
        public GroceryItem createFromParcel(Parcel pc) {
            return new GroceryItem(pc);
        }
        public GroceryItem[] newArray(int size) {
            return new GroceryItem[size];
        }
    };

    /**Ctor from Parcel, reads back fields IN THE ORDER they were written */
    public GroceryItem(Parcel pc){
        id         = pc.readLong();
        name       =  pc.readString();
        quantity   =  pc.readString();
        is_checked = ( pc.readInt() == 1 );
    }
}