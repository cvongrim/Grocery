package com.colinv.grocery;

/**
 * GroceryListAdapter
 * Created by colinv on 11/18/13.
 */
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

public class GroceryListAdapter extends BaseAdapter {

    private Activity activity;
    public ArrayList<GroceryItem> data = new ArrayList<GroceryItem>();
    private static LayoutInflater inflater=null;

    public GroceryListAdapter(Activity a, ArrayList<GroceryItem> d) {
        activity = a;
        data=d;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount() {
        return data.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View vi=convertView;
        if(convertView==null)
            vi = inflater.inflate(R.layout.list_row, null);

        TextView groceryTitle = (TextView) vi.findViewById(R.id.titleTextView);


        GroceryItem groceryItem;
        groceryItem = data.get(position);

        // Setting all values in listView
        groceryTitle.setText(groceryItem.getName());

        Log.d("Is Checked:", groceryItem.getIsChecked().toString());

        if(groceryItem.getIsChecked()){
            groceryTitle.setPaintFlags(groceryTitle.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            groceryTitle.setTextColor(Color.DKGRAY);
        }else{
            groceryTitle.setPaintFlags(groceryTitle.getPaintFlags() & (~ Paint.STRIKE_THRU_TEXT_FLAG));
            groceryTitle.setTextColor(Color.BLACK);
        }
        //artist.setText(song.get(CustomizedListView.KEY_ARTIST));

        return vi;
    }
}
