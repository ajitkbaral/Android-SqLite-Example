package com.leapfrog.mydatabaseapp.constant;

/**
 * Created by Ajit Kumar Baral on 9/1/2015.
 */
public class Constants {
    public static final String DATABASE_NAME="greetings.db";
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_TABLE_NAME="greeting";
    public static final String COLUMN_KEY_ID = "_id", COLUMN_ID="greeting_id";
    public static final String COLUMN_CONTENT = "content";
    public static final String CREATE_TABLE_GREETING="CREATE TABLE "+DATABASE_TABLE_NAME+"( "+COLUMN_KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
            COLUMN_ID+" INTEGER, "+COLUMN_CONTENT+" VARCHAR(255));";
    public static final String DROP_TABLE_GREETING = "DROP TABLE IF EXISTS "+DATABASE_TABLE_NAME;

}
