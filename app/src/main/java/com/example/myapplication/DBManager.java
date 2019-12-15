package com.example.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBManager extends SQLiteOpenHelper {
    public DBManager(@Nullable Context context) {
        super(context, "dbThuChi", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableThuChi = "create table KhoanThuChi(ten text primary key, soTien integer, loai text)";
        db.execSQL(createTableThuChi);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL("drop table if exists KhoanThuChi");
    onCreate(db);
    }
}
