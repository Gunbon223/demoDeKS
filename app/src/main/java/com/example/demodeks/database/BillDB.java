package com.example.demodeks.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.demodeks.entities.HotelBill;

import java.util.List;

public class BillDB extends SQLiteOpenHelper {
    private final static String DB_NAME="BillDB";
    private final static int VERSION_DB=1;

    public BillDB(@Nullable Context context) {
        super(context, DB_NAME,null,VERSION_DB);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS BillHotel (" +
                "id  INTEGER PRIMARY KEY AUTOINCREMENT," +
                "roomNumber int," +
                "DonGia double," +
                "SoNgay Integer," +
                "Hoten TEXT)";
                db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

public void addBill(HotelBill bill) {
    SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
    String insertSql = "INSERT INTO BillHotel(roomNumber, DonGia, SoNgay,Hoten) VALUES (" + bill.getRoomNumber() + "," + bill.getDonGia() + "," + bill.getSoNgay()  + ",'" + bill.getHoTen()+ "')";
    sqLiteDatabase.execSQL(insertSql);
}

    public List<HotelBill> getAllBill(List<HotelBill> Bill) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String sql = "SELECT * FROM BillHotel";
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        if(cursor.moveToFirst()) {
            do {
              HotelBill bill =new HotelBill(cursor.getInt(0),cursor.getInt(1),cursor.getDouble(2),cursor.getInt(3),cursor.getString(4));
              Bill.add(bill);

            }
        while (cursor.moveToNext());
                }
        return Bill;
    }





}
