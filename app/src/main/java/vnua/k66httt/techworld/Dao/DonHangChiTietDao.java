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

public class DonHangChiTietDao {
    private dbVnua dbVnua;

    public DonHangChiTietDao(Context context) {
        this.dbVnua = new dbVnua(context);
    }

    public ArrayList<DanhGia> getDanhGiaByMaSanPham(int maSanPham) {
        ArrayList<DanhGia> list = new ArrayList<>();
        SQLiteDatabase database = dbVnua.getReadableDatabase();
        try {
            String query = "SELECT " +
                    "DANHGIA.madanhgia, DANHGIA.mataikhoan, TAIKHOAN.hoten, DANHGIA.masanpham, SANPHAM.tensanpham, DANHGIA.danhgia, DANHGIA.nhanxet, DANHGIA.ngaydanhgia " +
                    "FROM DANHGIA " +
                    "INNER JOIN TAIKHOAN ON DANHGIA.mataikhoan = TAIKHOAN.mataikhoan " +
                    "INNER JOIN SANPHAM ON DANHGIA.masanpham = SANPHAM.masanpham " +
                    "WHERE DANHGIA.masanpham = ?";

            Cursor cursor = database.rawQuery(query, new String[]{String.valueOf(maSanPham)});

            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                do {
                    DanhGia danhGia = new DanhGia();
                    danhGia.setMaDanhGia(cursor.getInt(0));
                    danhGia.setMaTaiKhoan(cursor.getInt(1));
                    danhGia.setTenTaiKhoan(cursor.getString(2));
                    danhGia.setMaSanPham(cursor.getInt(3));
                    danhGia.setTenSanPham(cursor.getString(4));
                    danhGia.setDanhGia(cursor.getString(5));
                    danhGia.setNhanXet(cursor.getString(6));
                    danhGia.setNgayDanhGia(cursor.getString(7));

                    list.add(danhGia);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.d(TAG, "Lỗi", e);
        } finally {
            if (database != null && database.isOpen()) {
                database.close();
            }
        }
        return list;
    }
    public ArrayList<DanhGia> getAllDanhGia() {
        ArrayList<DanhGia> list = new ArrayList<>();
        SQLiteDatabase database = dbVnua.getReadableDatabase();

        try {
            String query = "SELECT " +
                    "DANHGIA.madanhgia, DANHGIA.mataikhoan, TAIKHOAN.hoten, DANHGIA.masanpham, SANPHAM.tensanpham, DANHGIA.danhgia, DANHGIA.nhanxet, DANHGIA.ngaydanhgia " +
                    "FROM DANHGIA " +
                    "INNER JOIN TAIKHOAN ON DANHGIA.mataikhoan = TAIKHOAN.mataikhoan " +
                    "INNER JOIN SANPHAM ON DANHGIA.masanpham = SANPHAM.masanpham";

            Cursor cursor = database.rawQuery(query, null);

            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                do {
                    DanhGia danhGia = new DanhGia();
                    danhGia.setMaDanhGia(cursor.getInt(0));
                    danhGia.setMaTaiKhoan(cursor.getInt(1));
                    danhGia.setTenTaiKhoan(cursor.getString(2));
                    danhGia.setMaSanPham(cursor.getInt(3));
                    danhGia.setTenSanPham(cursor.getString(4));
                    danhGia.setDanhGia(cursor.getString(5));
                    danhGia.setNhanXet(cursor.getString(6));
                    danhGia.setNgayDanhGia(cursor.getString(7));

                    list.add(danhGia);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.d(TAG, "Lỗi", e);
        } finally {
            if (database != null && database.isOpen()) {
                database.close();
            }
        }

        return list;
    }

    public boolean addDanhGia(int mataikhoan,int maSanPham, String danhGia, String nhanXet, String ngayDanhGia) {
        SQLiteDatabase database = dbVnua.getWritableDatabase();
        try {
            // Chuẩn bị dữ liệu để chèn vào bảng DANHGIA
            ContentValues values = new ContentValues();
            values.put("mataikhoan",mataikhoan);
            values.put("masanpham", maSanPham);
            values.put("danhgia", danhGia);
            values.put("nhanxet", nhanXet);
            values.put("ngaydanhgia", ngayDanhGia);
            long result = database.insert("DANHGIA", null, values);

            // Kiểm tra kết quả và trả về true nếu thành công, false nếu thất bại
            return result != -1;
        } catch (Exception e) {
            Log.d(TAG, "Lỗi khi thêm đánh giá", e);
        } finally {
            if (database != null && database.isOpen()) {
                database.close();
            }
        }

        // Trả về false nếu có lỗi xảy ra
        return false;
    }
}
