package com.leapfrog.mydatabaseapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.leapfrog.mydatabaseapp.constant.Constants;
import com.leapfrog.mydatabaseapp.dao.GreetingDAO;
import com.leapfrog.mydatabaseapp.entity.Greeting;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ajit Kumar Baral on 9/1/2015.
 */
public class GreetingDatabaseAdapter implements GreetingDAO{

    private DatabaseHelper databaseHelper;
    private Context context;

    public GreetingDatabaseAdapter(Context context){
        databaseHelper = new DatabaseHelper(context);
    }


    @Override
    public long insert(Greeting greeting) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Constants.COLUMN_ID, greeting.getId());
        contentValues.put(Constants.COLUMN_CONTENT, greeting.getContent());
        return db.insert(Constants.DATABASE_TABLE_NAME, null, contentValues);

    }

    @Override
    public int update(Greeting greeting) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Constants.COLUMN_ID, greeting.getId());
        contentValues.put(Constants.COLUMN_CONTENT, greeting.getContent());
        return db.update(Constants.DATABASE_TABLE_NAME, contentValues, Constants.COLUMN_KEY_ID+" = "+greeting.getId(), null);

    }

    @Override
    public int delete(int id) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        return db.delete(Constants.DATABASE_TABLE_NAME, Constants.COLUMN_KEY_ID+" = "+id, null);

    }

    @Override
    public Greeting getById(int id) {
        Greeting greeting = null;
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        String[] columns = {Constants.COLUMN_KEY_ID, Constants.COLUMN_ID, Constants.COLUMN_CONTENT};
        Cursor cursors = db.query(Constants.DATABASE_TABLE_NAME, columns, Constants.COLUMN_KEY_ID +" = "+id, null, null, null, null);
        if(cursors.moveToNext()){
            int keyId = cursors.getInt(cursors.getColumnIndex(Constants.COLUMN_KEY_ID));
            int  greetingId = cursors.getInt(cursors.getColumnIndex(Constants.COLUMN_ID));
            String content = cursors.getString(cursors.getColumnIndex(Constants.COLUMN_CONTENT));
            greeting = new Greeting(keyId, greetingId, content);
        }
        return greeting;
    }

    @Override
    public List<Greeting> getAll() {
        List<Greeting> greetingList = new ArrayList<>();
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        String[] columns = {Constants.COLUMN_KEY_ID, Constants.COLUMN_ID, Constants.COLUMN_CONTENT};

        Cursor cursors = db.query(Constants.DATABASE_TABLE_NAME, columns, null, null, null, null, null);
        while(cursors.moveToNext()){
            int keyId = cursors.getInt(cursors.getColumnIndex(Constants.COLUMN_KEY_ID));
            int  greetingId = cursors.getInt(cursors.getColumnIndex(Constants.COLUMN_ID));
            String content = cursors.getString(cursors.getColumnIndex(Constants.COLUMN_CONTENT));
            Greeting greeting = new Greeting(keyId, greetingId, content);
            greetingList.add(greeting);
        }

        return greetingList;
    }
}
