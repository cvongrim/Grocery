package com.colinv.grocery;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import android.database.SQLException;

/**
 * Database Creation Class
 * Created by Colin on 11/14/13.
 */
public class DBAdapter {
    static final String KEY_ROWID = "_id";
    static final String KEY_NAME = "name";
    static final String KEY_QUANTITY = "quantity";
    static final String KEY_IS_CHECKED = "is_checked";
    static final String TAG = "DBAdapter";

    static final String DATABASE_NAME = "GroceryPlus";
    static final String DATABASE_TABLE = "items";
    static final int DATABASE_VERSION = 5;

    static final String DATABASE_CREATE = "create table items (_id integer primary key autoincrement, " + "name text not null, quantity text not null, is_checked boolean);";

    final Context context;

    DatabaseHelper DBHelper;
    SQLiteDatabase db;

    public DBAdapter(Context ctx)
    {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }

    private static class DatabaseHelper extends SQLiteOpenHelper
    {
        DatabaseHelper(Context context)
        {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db)
        {
            try{
                db.execSQL(DATABASE_CREATE);
            } catch (SQLException e){
                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
        {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to " + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS items");
            onCreate(db);

        }
    }

    //---opens the database---
    public DBAdapter open() throws SQLException
    {
        db = DBHelper.getWritableDatabase();
        return this;
    }

    //---closes the database---
    public void close()
    {
        DBHelper.close();
    }

    //--insert an item into the database---
    public long insertItem(String name, String quantity)
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_NAME, name);
        initialValues.put(KEY_QUANTITY, quantity);
        initialValues.put(KEY_IS_CHECKED, false);
        return db.insert(DATABASE_TABLE, null, initialValues);
    }

    //---deletes a particular item---
    public boolean deleteItem(long rowId)
    {
        return db.delete(DATABASE_TABLE, KEY_ROWID + "=" + rowId, null) > 0;
    }

    //---retrieves all the contacts---
    public Cursor getAllItems()
    {
        return db.query(DATABASE_TABLE, new String[]{KEY_ROWID, KEY_NAME, KEY_QUANTITY, KEY_IS_CHECKED}, null, null, null, null,null,null);
    }

    //--retrieves a particular item---
    public Cursor getItem(long rowId) throws SQLException
    {
        Cursor mCursor = db.query(true, DATABASE_TABLE, new String[]{KEY_ROWID, KEY_NAME, KEY_QUANTITY, KEY_IS_CHECKED}, KEY_ROWID + "=" + rowId, null, null, null, null, null);
        if(mCursor != null){
            mCursor.moveToFirst();
        }

        return mCursor;
    }

    //---update an item---
    public boolean updateItem(long rowId, String name, String quantity, Boolean is_checked)
    {
        ContentValues args = new ContentValues();
        args.put(KEY_NAME, name);
        args.put(KEY_QUANTITY, quantity);
        args.put(KEY_IS_CHECKED, is_checked);
        return db.update(DATABASE_TABLE, args, KEY_ROWID + "=" + rowId, null) > 0;
    }

    //--delete and recreate table
    public boolean deleteAllRows(){
        return db.delete(DATABASE_TABLE, null , null) > 0;
    }


}
