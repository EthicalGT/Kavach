package com.example.signup_login;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class registerUser extends SQLiteOpenHelper {
    public Context reference;

    public registerUser(Context context) {
        super(context, "kavach", null, 2);
        reference = context;
    }

    String SQL = "CREATE TABLE users (fullname TEXT, mobileno VARCHAR(10) PRIMARY KEY, email TEXT, password VARCHAR(12), android_id TEXT UNIQUE, login_status INTEGER)";
    String SQL2 = "CREATE TABLE relatives (name TEXT, rmobileno VARCHAR(10) PRIMARY KEY, mobileno VARCHAR(10), FOREIGN KEY (mobileno) REFERENCES users(mobileno))";
    String SQL3 = "create table feedback (name TEXT, mobileno VARCHAR(10), msg text)";
    String SQL4="create table notifications(msg text, date date, time time)";
    //String SQL5="create table tutorials(filename text primary key, path text)";
    String SQL6="CREATE TABLE administrator(adminname TEXT PRIMARY KEY, password VARCHAR(12))";
    @Override
    public void onCreate(SQLiteDatabase db) {

        try {
            db.execSQL(SQL);
            db.execSQL(SQL2);
            db.execSQL(SQL3);
            db.execSQL(SQL4);
            //db.execSQL(SQL5);
            db.execSQL(SQL6);
            db.execSQL("insert into administrator values('prashanttelore','Prashant@04')");
            //db.execSQL("insert into tutorials values('Jijutsu Techniques','android.resource://com.example.signup_login/21311755008')");
            Toast.makeText(reference, "Kavach is Ready to Go!", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(reference,"Error: "+e,Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int OldVersion, int newVersion) {

    }

    public boolean addData(String data1, String data2, String data3, String data4, String data5) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("fullname", data1);
        cv.put("mobileno", data2);
        cv.put("email", data3);
        cv.put("password", data4);
        cv.put("android_id", data5);
        cv.put("login_status", 1);
        long res = db.insert("users", null, cv);
        if (res != -1) {
            Toast.makeText(reference, "Signed Up Successfully", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(reference, "Error Occured!!!", Toast.LENGTH_LONG).show();
        }
        return res != -1;
    }
    public boolean send_feedback(String name, String mobileno, String msg){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("name",name);
        cv.put("mobileno",mobileno);
        cv.put("msg",msg);
        long res=db.insert("feedback",null,cv);
        if(res!=-1)
            Toast.makeText(reference, "Feedback Successfull!", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(reference, "Error Occured!!!", Toast.LENGTH_LONG).show();

        return res!=-1;
    }
    public boolean CheckUserTable() {
        boolean res = false;
        SQLiteDatabase db = this.getWritableDatabase();

        // Check if the "users" table exists
        Cursor tableCursor = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='users'", null);
        if (tableCursor != null) {
            if (tableCursor.getCount() > 0) { // "users" table exists
                tableCursor.close();

                // Check if there are any rows in the "users" table
                Cursor dataCursor = db.rawQuery("SELECT COUNT(*) FROM users", null);
                if (dataCursor != null) {
                    if (dataCursor.moveToFirst()) {
                        int count = dataCursor.getInt(0);
                        res = count == 0; // If count is 0, table has no data
                    }
                    dataCursor.close();
                }
            } else { // "users" table doesn't exist
                res = true;
            }
            tableCursor.close();
        }

        return res;
    }



    public boolean validateLogin(String id) {
        boolean res = false;
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "select android_id from users where android_id=?";
        String[] selection = {id};
        Cursor cr = db.rawQuery(query, selection);
        if (cr != null && cr.moveToFirst()) {
            int android = cr.getColumnIndex("android_id");
            if (android != -1) {
                String android_id = cr.getString(android);
                if (id.equals(android_id)) {
                    res = true;
                }
            }
        }
        if (cr != null) {
            cr.close();
        }
        return res;
    }

    public String getLoggedUserMobile() {
        String mobileNo = "";
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "select mobileno from users where login_status=?";
        String[] selection = {"1"};
        Cursor cr = db.rawQuery(query, selection);
        if (cr != null && cr.moveToFirst()) {
            int mobile = cr.getColumnIndex("mobileno");
            if (mobile != -1) {
                mobileNo = cr.getString(mobile);
            }
        }
        if (cr != null) {
            cr.close();
        }
        return mobileNo;
    }

    public List<String> sendRelativeSMS(String data) {
        List<String> relativeMobileNumbers = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT rmobileno FROM relatives WHERE mobileno=?";
        String[] selection = {data};
        Cursor cr = db.rawQuery(query, selection);
        if (cr != null && cr.moveToFirst()) {
            do {
                int mobile = cr.getColumnIndex("rmobileno");
                if (mobile != -1) {
                    String relativeMobileNumber = cr.getString(mobile);
                    relativeMobileNumbers.add(relativeMobileNumber);
                }
            } while (cr.moveToNext());
        }
        if (cr != null) {
            cr.close();
        }
        return relativeMobileNumbers;
    }

    public boolean addRelative(String data1, String data2, String data3) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name", data1);
        cv.put("rmobileno", data2);
        cv.put("mobileno", data3);
        long res = db.insert("relatives", null, cv);
        if (res != -1) {
            Toast.makeText(reference, "Relative Added Successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(reference, "Error Occurred!!!", Toast.LENGTH_SHORT).show();
        }
        db.close();
        return res != -1;
    }



    public boolean validateLogin(String d1, String d2) {
        boolean res = false;
        SQLiteDatabase db = this.getReadableDatabase();
        String SQL = "SELECT * FROM users WHERE mobileno=? AND password=?";
        String[] selection = {d1, d2};
        Cursor cr = db.rawQuery(SQL, selection);
        if (cr != null && cr.moveToFirst()) {
            int mobile = cr.getColumnIndex("mobileno");
            int password = cr.getColumnIndex("password");
            if (mobile != -1 && password != -1) {
                String mobileNo = cr.getString(mobile);
                String pwd = cr.getString(password);
                if (d1.equals(mobileNo) && d2.equals(pwd)) {
                    res = true;
                }
            }
        }
        if (cr != null) {
            cr.close();
        }
        db.close();
        return res;
    }
    public String validateLoggedInUser() {
        String res = "";
        SQLiteDatabase db = this.getReadableDatabase();
        String SQL = "SELECT fullname FROM users WHERE login_status=?";
        String[] selection = {"1"};
        Cursor cr = db.rawQuery(SQL, selection);
        if (cr != null && cr.moveToFirst()) {
            int nameIndex = cr.getColumnIndex("fullname");
            if (nameIndex != -1) {
                res = cr.getString(nameIndex);
            }
        }
        if (cr != null) {
            cr.close();
        }
        db.close();
        return res;
    }
    /*public String tutorialfetch(String name){
        String res="";
        SQLiteDatabase db=this.getReadableDatabase();
        String sql="select * from tutorials where filename=?";
        String[] sel={name};
        Cursor cr=db.rawQuery(sql,sel);
        if (cr != null && cr.moveToFirst()) {
            int nameIndex = cr.getColumnIndex("filename");
            if (nameIndex != -1) {
                res = cr.getString(nameIndex);
            }
        }
        if (cr != null) {
            cr.close();
        }
        db.close();
        return res;
    }*/
}