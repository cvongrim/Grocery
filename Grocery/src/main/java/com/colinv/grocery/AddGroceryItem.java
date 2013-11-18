package com.colinv.grocery;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * Add a new item to the grocery list
 */
public class AddGroceryItem extends Fragment {

    DBAdapter db;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_add_grocery_item, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();


    }


}
