/**
 * Created by ivanornes on 08/07/14.
 */
package com.ayalavalley.laudiokojaiak.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DBOperations {
    private static final String TAG = DBOperations.class.getSimpleName();
    private DBHelper dbHelper;

    public DBOperations(Context context){
        dbHelper = new DBHelper(context);
    }

    public void insertOrIgnore(ContentValues values, String table){

        SQLiteDatabase dataBase = dbHelper.getWritableDatabase();
        try{
            dataBase.insertWithOnConflict(table, null, values, SQLiteDatabase.CONFLICT_REPLACE);
        }finally{
            dataBase.close();
        }
    }
}
