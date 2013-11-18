package com.colinv.grocery;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class mainActivity extends ListActivity {

    DBAdapter db;
    String[] groceryItems;
    ArrayList data = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        db = new DBAdapter(this);

        // AddContact();
        GetContacts();

        // TODO: MAKE THIS POPULATE FORM SQL LITE DATABASE
        /*String[] groceryItems = {
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
        };*/


       this.setListAdapter(new ArrayAdapter<String>(this, R.layout.list_row, R.id.titleTextView, data));

       ListView lv = getListView();

        // listening to single list item on click
        lv.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                // TODO: MAKE STRIKE THROUGH TOGGLE
                TextView groceryTitle = (TextView) view.findViewById(R.id.titleTextView);
                groceryTitle.setPaintFlags(groceryTitle.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

            }

        });

    }

    public void AddContact(){
        db.open();
        if(db.insertItem("Puddings") >= 0){
            Toast.makeText(this, "Add successful.", Toast.LENGTH_LONG).show();
        }
        db.close();
    }

    public void GetContacts(){
        db.open();
        Cursor c = db.getAllItems();
        String item;
        if(c.moveToFirst())
        {
            do {
               data.add(DisplayContact(c));

            }while( c.moveToNext());
        }

        db.close();
    }

    public String DisplayContact(Cursor c)
    {
        return  c.getString(1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.grocery_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.addGroceryActivity:
                openAddGroceryActivity();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void openAddGroceryActivity(){
        // TODO: OPEN AddGroceryItem
        Intent i = new Intent("com.colinv.grocery.AddGroceryItem");
        startActivity(i);

    }

}
