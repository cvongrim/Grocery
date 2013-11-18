package com.colinv.grocery;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Toast;

/**
 * Add a new item to the grocery list
 */
public class AddGroceryItem extends FragmentActivity{

    DBAdapter db;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_grocery_item);
    }

    public void AddContact(View view){

        //addItem();
        finish();
    }

    public void addItem(){
        db.open();
        if(db.insertItem("Grapes") >= 0){
            Toast.makeText(this, "Add successful.", Toast.LENGTH_LONG).show();
        }
        db.close();
    }

}
