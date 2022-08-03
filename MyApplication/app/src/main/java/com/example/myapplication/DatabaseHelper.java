package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "perpusonline.db";
    private static final String DB_TABLE_NAME_USER = "User_Table";

    private static final String userId = "USER_ID";
    private static final String userPhone = "USER_PHONE";
    private static final String userPassword = "USER_PASSWORD";
    private static final String userEmail = "USER_EMAIL";
    private static final String userDOB = "USER_DOB";
    private static final String CREATE_TABLE_USER = "CREATE TABLE " + DB_TABLE_NAME_USER + "(" + userId +
            " INTEGER PRIMARY KEY AUTOINCREMENT, " + userPhone + " TEXT, " + userEmail + " TEXT, " + userPassword + " TEXT, "
            + userDOB + " TEXT)";
    private final static String DB_TABLE_NAME_REQ = "Request_Table";
    private static final String reqId = "REQUEST_ID";
    private static final String reqTitle = "REQUEST_TITLE";
    private static final String reqAuthor = "REQUEST_AUTHOR";
    private static final String reqSynopsis = "REQUEST_SYNOPSIS";
    private static final String reqEmail = "REQUEST_EMAIL";
    private static final String recEmail = "RECEIVER_EMAIL";
    private static final String reqLatitude = "REQUEST_LATITUDE";
    private static final String reqLongitude = "REQUEST_LONGITUDE";
    private static final String reqPhone = "REQUEST_PHONE";
    private static final String CREATE_TABLE_REQ = "CREATE TABLE " + DB_TABLE_NAME_REQ + "( " + reqId +
            " INTEGER PRIMARY KEY AUTOINCREMENT , " + reqTitle + " TEXT, " + reqAuthor + " TEXT, " +
            reqSynopsis + " TEXT, " + reqEmail + " TEXT, " + recEmail + " TEXT, " + reqLatitude + " DECIMAL(10, 30), " +
            reqLongitude + " DECIMAL(10, 30), " + reqPhone + " TEXT)";

    public DatabaseHelper(Context con){
        super(con, DB_NAME, null, 4);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_USER);
        sqLiteDatabase.execSQL(CREATE_TABLE_REQ);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DB_TABLE_NAME_USER);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DB_TABLE_NAME_REQ);
        onCreate(sqLiteDatabase);
    }

    public boolean insertDataUser(String email, String password, String phone, String dob){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        //cv.put(userId, id);
        cv.put(userPhone, phone);
        cv.put(userEmail, email);
        cv.put(userPassword, password);
        cv.put(userDOB, dob);

        long res = database.insert(DB_TABLE_NAME_USER, null, cv);

        return res != -1;
    }

    public boolean insertDataRequest(String title, String author, String synopsis, String requester,
                                     String receiver, double latitude, double longitude, String phone){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        //cv.put(userId, id);
        cv.put(reqTitle, title);
        cv.put(reqAuthor, author);
        cv.put(reqSynopsis, synopsis);
        cv.put(reqEmail, requester);
        cv.put(recEmail, receiver);
        cv.put(reqLatitude, latitude);
        cv.put(reqLongitude, longitude);
        cv.put(reqPhone, phone);

        long res = database.insert(DB_TABLE_NAME_REQ, null, cv);

        return res != -1;
    }

    public void updateReceiver(String receiver, int id){
        SQLiteDatabase database = this.getReadableDatabase();
        database.execSQL("UPDATE " + DB_TABLE_NAME_REQ + " SET " + recEmail + " = " + receiver +
                " WHERE " + reqId + " = " + id);
        database.close();
    }

    public Cursor viewDataUser(){
        SQLiteDatabase database = this.getReadableDatabase();
        String selectAll = "SELECT * FROM " + DB_TABLE_NAME_USER;
        Cursor cur = database.rawQuery(selectAll, null);

        return cur;
    }

    public Cursor viewDataRequest(){
        SQLiteDatabase database = this.getReadableDatabase();
        String selectAll = "SELECT * FROM " + DB_TABLE_NAME_REQ;
        Cursor cur = database.rawQuery(selectAll, null);

        return cur;
    }


    public void deleteData(int id){
        SQLiteDatabase database = this.getWritableDatabase();
        database.execSQL("DELETE FROM " + DB_TABLE_NAME_USER + " WHERE " + userId + " = " + id);
        database.close();
    }

    public void deleteAllData(){
        SQLiteDatabase database = this.getWritableDatabase();
        database.execSQL("DELETE FROM " + DB_TABLE_NAME_USER);
        database.close();
    }
}
