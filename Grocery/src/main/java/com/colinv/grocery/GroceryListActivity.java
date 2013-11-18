package com.colinv.grocery;

import android.database.Cursor;
import android.support.v4.app.ListFragment;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

/**
 *  Display Grocery Items
 */
public class GroceryListActivity extends ListFragment{


    ArrayList data = new ArrayList();
    GroceryListAdapter adapter;

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
        groceryTitle.setPaintFlags(groceryTitle.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        groceryTitle.setTextColor(getResources().getColor(R.color.pressed_color));

        //Collections.swap(data, position, 0);
        //setListAdapter(new ArrayAdapter<String>(getActivity(), R.layout.list_row, R.id.titleTextView, data));
    }

}
