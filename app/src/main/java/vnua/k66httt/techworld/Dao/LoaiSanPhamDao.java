package vnua.k66httt.techworld.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import vnua.k66httt.techworld.Database.dbVnua;


import vnua.k66httt.techworld.Model.LoaiSanPham;

public class LoaiSanPhamDao {
    private SQLiteDatabase db;
    dbVnua dbVnua;

    public LoaiSanPhamDao(Context context) {
        dbVnua dbHelper = new dbVnua(context);
        db = dbHelper.getWritableDatabase();
    }

    public boolean insert(String tenloai) {

        ContentValues values = new ContentValues();
        values.put("tenloaisanpham", tenloai);
        long check = db.insert("LOAISANPHAM", null, values);
        if (check == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean update(LoaiSanPham loaiSanPham) {
        ContentValues values = new ContentValues();
        values.put("tenloaisanpham", loaiSanPham.getTenloaisp());
        long check = db.update("LOAISANPHAM", values, "maloaisanpham=?", new String[]{String.valueOf(loaiSanPham.getMaloaisp())});
        if (check == -1) {
            return false;
        } else {
            return true;
        }
    }

    public int delete(int maloai) {
        db = dbVnua.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from SANPHAM where maloaisanpham = ?", new String[]{String.valueOf(maloai)});
        if (cursor.getCount() != 0) {
            return -1;
        }
        long check = db.delete("LOAISANPHAM", "maloaisanpham = ?", new String[]{String.valueOf(maloai)});
        if (check == -1) {
            return 0;
        } else {
            return 1;
        }
//      long row=  db.delete("LOAISANPHAM","maloaisanpham=?",new String[]{String.valueOf(loaiSanPham.getMaloaisp())});
//        return (row>0);
    }

    public ArrayList<LoaiSanPham> getalltheloai() {
        ArrayList<LoaiSanPham> list = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT *FROM LOAISANPHAM", null);
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            do {
                list.add(new LoaiSanPham(cursor.getInt(0), cursor.getString(1)));
            } while (cursor.moveToNext());
        }
        return list;
    }

}
