package com.colinv.grocery;

import android.database.Cursor;
import android.graphics.Color;
import android.support.v4.app.ListFragment;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

/**
 *  Display Grocery Items
 */
public class GroceryListActivity extends ListFragment{


    ArrayList<GroceryItem> data = new ArrayList<GroceryItem>();
    GroceryListAdapter adapter;
    DBAdapter db;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        data = getArguments().getParcelableArrayList("groceryList");
        adapter = new GroceryListAdapter(getActivity(), data);
        setListAdapter(adapter);
       // setListAdapter(new ArrayAdapter<String>(getActivity(), R.layout.list_row, R.id.titleTextView, data));

    }

    public void onListItemClick(ListView parent, View v, int position, long id){
        TextView groceryTitle = (TextView) v.findViewById(R.id.titleTextView);

        // Get Grocery List
        db = new DBAdapter(getActivity());


        GroceryItem groceryItem;
        groceryItem = data.get(position);

        Log.d("Is Checked:", groceryItem.getIsChecked().toString());

        db.open();


        if (groceryItem.getIsChecked()) {
            db.updateItem(groceryItem.getId(), groceryItem.getName(), groceryItem.getQuantity(), false);
            groceryTitle.setPaintFlags(groceryTitle.getPaintFlags() & (~ Paint.STRIKE_THRU_TEXT_FLAG));
            groceryTitle.setTextColor(Color.BLACK);

        } else {
            db.updateItem(groceryItem.getId(), groceryItem.getName(), groceryItem.getQuantity(), true);
            groceryTitle.setPaintFlags(groceryTitle.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            groceryTitle.setTextColor(Color.GRAY);

        }
                db.close();

        //Collections.swap(data, position, 0);
        //setListAdapter(new ArrayAdapter<String>(getActivity(), R.layout.list_row, R.id.titleTextView, data));
    }

}
