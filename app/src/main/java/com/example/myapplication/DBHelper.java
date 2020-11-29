package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context)
    {
        super(context, "users.db" , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE  User(id integer primary key,site VARCHAR,pass VARCHAR);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS User");
        onCreate(db);
    }
    public boolean insertData(Integer id,String site,String pass)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("id",id);
        contentValues.put("site",site);
        contentValues.put("pass",pass);
        db.insert("User",null,contentValues);
        return true;
    }
    public Cursor getData(int id)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor res=db.rawQuery("select * from User where id="+id+"",null);
        return  res;

    }
    public int numberOfRows()
    {
        SQLiteDatabase db=this.getReadableDatabase();
        int nrows=(int) DatabaseUtils.queryNumEntries(db,"User");
        return nrows;
    }
    public boolean updateData(Integer id,String site,String pass)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("site",site);
        contentValues.put("pass",pass);
        db.update("User",contentValues,"id = ?",
                new String[]{Integer.toString(id)});
        return true;
    }
    public Integer deleteContact (Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("contacts",
                "id = ? ",
                new String[] { Integer.toString(id) });
    }
public ArrayList<String> getAll()
{
    ArrayList<String> list=new ArrayList<>();
    SQLiteDatabase db=this.getReadableDatabase();
    Cursor res=db.rawQuery("select * from User",null);
    res.moveToFirst();
    while(!res.isAfterLast())
    {
        list.add(res.getString(res.getColumnIndex("site")));
        res.moveToNext();
    }
    res.close();
    return list;
}
    public ArrayList<String> getAllP()
    {
        ArrayList<String> list=new ArrayList<>();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor res=db.rawQuery("select * from User",null);
        res.moveToFirst();
        while(!res.isAfterLast())
        {
            list.add(res.getString(res.getColumnIndex("pass")));
            res.moveToNext();
        }
        res.close();
        return list;
    }
}
