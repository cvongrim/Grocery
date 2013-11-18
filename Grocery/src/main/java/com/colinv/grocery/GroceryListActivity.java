package com.colinv.grocery;

import android.app.ListActivity;
import android.app.ListFragment;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 *  Display Grocery Items
 */
public class GroceryListActivity extends ListActivity {


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_grocery_list);

        // TODO: MAKE THIS POPULATE FORM SQL LITE DATABASE
        String[] groceryItems = {
                "Apples",
                "Beef",
                "Rice",
                "Bread",
                "Chicken",
                "Noodles",
                "Eggs",
                "Apples",
                "Beef",
                "Rice",
                "Bread",
                "Chicken",
                "Noodles",
                "Eggs",
                "Apples",
                "Beef",
                "Rice",
                "Bread",
                "Chicken",
                "Noodles",
                "Eggs"
        };

        this.setListAdapter(new ArrayAdapter<String>(this, R.layout.list_row, R.id.titleTextView, groceryItems));

        ListView lv = getListView();

        // listening to single list item on click
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO: MAKE STRIKE THROUGH TOGGLE
                TextView groceryTitle = (TextView) view.findViewById(R.id.titleTextView);
                groceryTitle.setPaintFlags(groceryTitle.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

            }

        });

    }

    public void onListItemClick(ListView parent, View v, int position, long id){

    }
}
