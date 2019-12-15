package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class ThuChiDAO {
    private DBManager dbManager;
    private SQLiteDatabase sqLiteDB;
    private Context context;

    public ThuChiDAO(Context context){
        this.context = context;
        dbManager = new DBManager(context);
        sqLiteDB = dbManager.getWritableDatabase();
    }
    public boolean insertThuChi(model thuChi){
        try {
            ContentValues values = new ContentValues();
            values.put("ten", thuChi.getTenKhoan());
            values.put("soTien", thuChi.getSoTien());
            values.put("loai", thuChi.getLoai());
            long numInsert = sqLiteDB.insert("KhoanThuChi", null, values);
            if (numInsert<=0)
                return false;
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
        return true;
    }
public List<model> getAllThuChi(){
        List<model> danhsachthuchi = new ArrayList<>();
        String sqlSelect = "select ten, soTien, loai from KhoanThuChi";
        Cursor cursor = sqLiteDB.rawQuery(sqlSelect, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            String ten = cursor.getString(0);
            String tien = cursor.getString(1);
            String loai = cursor.getString(2);
            model thuchi = new model(ten, tien, loai);
            danhsachthuchi.add(thuchi);
            cursor.moveToNext();
        }
        return danhsachthuchi;
}
public int deleteThuChi(String id){
    return  sqLiteDB.delete("KhoanThuChi","ten=?", new String[]{id});
}
public int updateThuChi(model thuchi){
        ContentValues contentValues = new ContentValues();
        contentValues.put("ten",thuchi.getTenKhoan() );
        contentValues.put("soTien", thuchi.getSoTien());
        contentValues.put("loai",thuchi.getLoai() );
        return sqLiteDB.update("KhoanThuChi", contentValues,"ten=?", new String[]{thuchi.getTenKhoan()});

}

}
