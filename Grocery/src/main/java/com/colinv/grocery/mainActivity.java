package com.colinv.grocery;

import android.database.Cursor;
import android.os.Bundle;
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
    public ArrayList<GroceryItem> data = new ArrayList<GroceryItem>();

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

            GetGroceryItems();

            // In case this activity was started with special instructions from an
            // Intent, pass the Intent's extras to the fragment as arguments
            Bundle args = new Bundle();

            args.putParcelableArrayList("groceryList", data);

            firstFragment.setArguments(args);

            // Add the fragment to the 'fragment_container' FrameLayout
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, firstFragment).commit();
        }


    }

    /**
     * Action Bar Code
     */

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
                //item.setVisible(false);
                return true;
            case R.id.clearList:
                clearGroceryList();
                return true;
            case android.R.id.home:
                openActivityList();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }



   // This is the function that the Add buttons on the AddGroceryItem activity calls

    public void AddGroceryItemButton(View view){
        addItem();
        openActivityList();
    }




    /**
     * Grocery List Functions
     */


    // Clear Grocery List
    public void clearGroceryList(){
        db = new DBAdapter(this);
        db.open();
        db.deleteAllRows();
        db.close();
        openActivityList();
    }

    // Add Grocery Item to Database
    public void addItem(){

        EditText itemEditText = (EditText) findViewById(R.id.itemEditText);
        EditText quantityEditText = (EditText) findViewById(R.id.quantityEditText);

        // Make sure that the field is not empty
        if(itemEditText.getText() != null || itemEditText.getText().toString() != ""){
            String itemName = itemEditText.getText().toString();
            String itemQuantity = quantityEditText.getText().toString();

            db = new DBAdapter(this);
            db.open();

            // Insert the item into the database
            if(db.insertItem(itemName, itemQuantity) >= 0){
                // Show Success message
                Toast.makeText(this, "Add successful.", Toast.LENGTH_LONG).show();
            }

            db.close();
        }else{
            //
            Toast.makeText(this, "Please enter an item.", Toast.LENGTH_LONG).show();
        }
    }

    /** Populate the data so we can build our list */

    public void GetGroceryItems(){
        db.open();
        Cursor c = db.getAllItems();
        data.clear();

        if(c.moveToFirst())
        {
            do {
                GroceryItem groceryItem = new GroceryItem();
                groceryItem.setId(displayId(c));
                groceryItem.setName(displayItemName(c));
                groceryItem.setQuantity(displayItemQuantity(c));
                groceryItem.setIsChecked(displayIsChecked(c));

                data.add(groceryItem);

            }while( c.moveToNext());
        }

        db.close();
    }

    public Long displayId(Cursor c)
    {
        return  c.getLong(0);
    }

    public String displayItemName(Cursor c)
    {
        return  c.getString(1);
    }

    public String displayItemQuantity(Cursor c)
    {
        return  c.getString(2);
    }

    public Boolean displayIsChecked(Cursor c)
    {
        return  c.getInt(3) > 0;
    }


     /**
     * FRAGMENTS
     */
    /** Load the GroceryListActivity Fragment */

    public void openActivityList() {
        // The user selected the headline of an article from the HeadlinesFragment
        // Do something here to display that article
        // Create fragment and give it an argument specifying the article it should show
        GroceryListActivity groceryListFragment = new GroceryListActivity();


        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        // Get Grocery List
        db = new DBAdapter(this);
        //AddContact();
        GetGroceryItems();

        // In case this activity was started with special instructions from an
        // Intent, pass the Intent's extras to the fragment as arguments
        Bundle args = new Bundle();

        args.putParcelableArrayList("groceryList", data);

        groceryListFragment.setArguments(args);

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack so the user can navigate back
        transaction.replace(R.id.fragment_container, groceryListFragment);
        transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();

        // get action bar and enable back button
        this.getActionBar().setDisplayHomeAsUpEnabled(false);
    }

    /** Load the AddGroceryActivity Fragment */
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
}
