package com.example.signup_login;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class administratorDB extends SQLiteOpenHelper {
    private Context context;
    public String val1,val2;

    public administratorDB(Context context) {
        super(context, "kavach", null, 2);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean validateadmin(String admin, String pwd) {
        boolean res = false;
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT adminname, password FROM administrator WHERE adminname=? AND password=?";
        String[] selection = {admin, pwd};
        Cursor cr = db.rawQuery(query, selection);
        if (cr != null && cr.moveToFirst()) {
            int adname = cr.getColumnIndex("adminname");
            int adpwd = cr.getColumnIndex("password");
            if (adname != -1 && adpwd != -1) {
                String adminname = cr.getString(adname);
                String password = cr.getString(adpwd);
                val1=adminname;
                val2=password;
                Toast.makeText(context,"admin :"+adminname+" pass: "+password,Toast.LENGTH_LONG).show();
                if (adminname.equals(admin) && password.equals(pwd)) {
                    res = true;
                }
            }
        }
        if (cr != null) {
            cr.close();
        }
        return res;
    }
    public boolean notify(String msg){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        SimpleDateFormat sdfDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
        String cdate = sdfDate.format(new Date());
        String ctime = sdfTime.format(new Date());
        cv.put("msg",msg);
        cv.put("date",cdate);
        cv.put("time",ctime);
        long res=db.insert("notifications",null,cv);
        if(res!=-1)
            return true;
        else
            return false;
    }
}
