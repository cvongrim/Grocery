package com.colinv.grocery;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Add a new item to the grocery list
 */
public class AddGroceryItem extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_add_grocery_item, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        // get action bar and enable back button
        getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);


    }
}
