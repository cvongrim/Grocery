package com.colinv.grocery;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;


public class mainActivity extends ActionBarActivity{

    DBAdapter db;
    public ArrayList<String> data = new ArrayList<String>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_grocery_list);

        // Check that the activity is using the layout version with
        // the fragment_container FrameLayout
        if (findViewById(R.id.fragment_container) != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }

            // Create a new Fragment to be placed in the activity layout
            GroceryListActivity firstFragment = new GroceryListActivity();

            // Get Grocery List
            db = new DBAdapter(this);
            //AddContact();
            GetContacts();

            // In case this activity was started with special instructions from an
            // Intent, pass the Intent's extras to the fragment as arguments
            Bundle args = new Bundle();

            args.putStringArrayList("groceryList", data);

            firstFragment.setArguments(args);

            // Add the fragment to the 'fragment_container' FrameLayout
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, firstFragment).commit();
        }
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
        data.clear();

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
        AddGroceryItem newFragment = new AddGroceryItem();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack so the user can navigate back
        transaction.replace(R.id.fragment_container, newFragment);
        transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();

    }

    public void onListItemSaved() {
        // The user selected the headline of an article from the HeadlinesFragment
        // Do something here to display that article
        // Create fragment and give it an argument specifying the article it should show
        GroceryListActivity groceryListFragment = new GroceryListActivity();


        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        // Get Grocery List
        db = new DBAdapter(this);
        //AddContact();
        GetContacts();

        // In case this activity was started with special instructions from an
        // Intent, pass the Intent's extras to the fragment as arguments
        Bundle args = new Bundle();

        args.putStringArrayList("groceryList", data);

        groceryListFragment.setArguments(args);

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack so the user can navigate back
        transaction.replace(R.id.fragment_container, groceryListFragment);
        transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();
    }

    public void AddContact(View view){
        addItem();
        onListItemSaved();
    }

    public void addItem(){
        db = new DBAdapter(this);
        db.open();

        EditText itemEditText = (EditText) findViewById(R.id.itemEditText);
        String itemName = itemEditText.getText().toString();

        if(db.insertItem(itemName) >= 0){
            Toast.makeText(this, "Add successful.", Toast.LENGTH_LONG).show();
        }
        db.close();

    }

}
