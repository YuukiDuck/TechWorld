package vnua.k66httt.techworld.Dao;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import vnua.k66httt.techworld.Database.dbVnua;
import vnua.k66httt.techworld.Model.DanhGia;
import vnua.k66httt.techworld.Model.DonHangChiTiet;

public class DonHangChiTietDao {
    private dbVnua dbVnua;

    public DonHangChiTietDao(Context context) {
        this.dbVnua = new dbVnua(context);
    }

    public ArrayList<DonHangChiTiet> getChiTietDonHangByMaDonHang(int maDonHang) {
        ArrayList<DonHangChiTiet> listChiTiet = new ArrayList<>();
        SQLiteDatabase database = dbVnua.getWritableDatabase();
        try {
            String query = "SELECT CHITIETDONHANG.machitietdonhang,CHITIETDONHANG.masanpham,SANPHAM.tensanpham,DONHANG.madonhang,CHITIETDONHANG.soluong,CHITIETDONHANG.dongia, CHITIETDONHANG.thanhtien,SANPHAM.anhsanpham FROM CHITIETDONHANG INNER JOIN DONHANG ON CHITIETDONHANG.madonhang = DONHANG.madonhang INNER JOIN SANPHAM ON CHITIETDONHANG.masanpham = SANPHAM.masanpham WHERE DONHANG.madonhang = ?";

            Cursor cursor = database.rawQuery(query, new String[]{String.valueOf(maDonHang)});

            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                do {
                    DonHangChiTiet chiTietDonHang = new DonHangChiTiet();
                    chiTietDonHang.setMaChiTietDonHang(cursor.getInt(0));
                    chiTietDonHang.setMaSanPham(cursor.getInt(1));
                    chiTietDonHang.setTenSanPham(cursor.getString(2));
                    chiTietDonHang.setMaDonHang(cursor.getInt(3));
                    chiTietDonHang.setSoLuong(cursor.getInt(4));
                    chiTietDonHang.setDonGia(cursor.getInt(5));
                    chiTietDonHang.setThanhTien(cursor.getInt(6));
                    chiTietDonHang.setAnhsanpham(cursor.getString(7));
                    listChiTiet.add(chiTietDonHang);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e(TAG, "Lỗi", e);
        } finally {
            database.close();
        }
        return listChiTiet;
    }


    public boolean xoaDonHang(DonHangChiTiet donHang) {
        SQLiteDatabase sqLiteDatabase = dbVnua.getWritableDatabase();
        long check = sqLiteDatabase.delete("CHITIETDONHANG", "machitietdonhang = ?", new String[]{String.valueOf(donHang.getMaChiTietDonHang())});
        return check > 0;

    }

    public boolean insertDonHangChiTiet(DonHangChiTiet donHangChiTiet) {
        SQLiteDatabase sqLiteDatabase = dbVnua.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("madonhang", donHangChiTiet.getMaDonHang());
        values.put("masanpham", donHangChiTiet.getMaSanPham());
        values.put("soluong", donHangChiTiet.getSoLuong());
        values.put("dongia", donHangChiTiet.getDonGia());
        values.put("thanhtien", donHangChiTiet.getThanhTien());

        long check = sqLiteDatabase.insert("CHITIETDONHANG", null, values);
        return check > 0;
    }

    public boolean updateDonHangChiTiet(DonHangChiTiet donHangChiTiet) {
        SQLiteDatabase sqLiteDatabase = dbVnua.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("madonhang", donHangChiTiet.getMaDonHang());
        values.put("masanpham", donHangChiTiet.getMaSanPham());
        values.put("soluong", donHangChiTiet.getSoLuong());
        values.put("dongia", donHangChiTiet.getDonGia());
        values.put("thanhtien", donHangChiTiet.getThanhTien());

        long check = sqLiteDatabase.update("CHITIETDONHANG", values, "machitietdonhang = ?", new String[]{String.valueOf(donHangChiTiet.getMaChiTietDonHang())});
        return check > 0;
    }


}