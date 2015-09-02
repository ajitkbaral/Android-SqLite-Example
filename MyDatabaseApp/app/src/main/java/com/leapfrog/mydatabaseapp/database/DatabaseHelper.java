package com.leapfrog.mydatabaseapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.leapfrog.mydatabaseapp.constant.Constants;

/**
 * Created by Ajit Kumar Baral on 9/1/2015.
 */
public class DatabaseHelper extends SQLiteOpenHelper{

    private Context context;


    public DatabaseHelper(Context context) {
        super(context, Constants.DATABASE_NAME, null, Constants.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Constants.CREATE_TABLE_GREETING);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(Constants.DROP_TABLE_GREETING);
        onCreate(db);

    }
}
