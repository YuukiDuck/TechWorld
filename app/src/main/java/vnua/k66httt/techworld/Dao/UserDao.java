package vnua.k66httt.techworld.Dao;

import static android.content.ContentValues.TAG;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import vnua.k66httt.techworld.Database.dbVnua;
import vnua.k66httt.techworld.Model.User;

public class UserDao {
    private final dbVnua dbVnua;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public UserDao(Context context) {
        this.dbVnua = new dbVnua(context);
        if (context != null) {
            sharedPreferences = context.getSharedPreferences("NGUOIDUNG", context.MODE_PRIVATE);
        } else {
            // Xử lý khi context là null, có thể thông báo lỗi hoặc thực hiện xử lý phù hợp
            Log.e(TAG, "Context is null in NguoiDungDao constructor");
        }

    }

    public ArrayList<User> getAllUser() {

        ArrayList<User> list = new ArrayList<>();
        SQLiteDatabase db = dbVnua.getReadableDatabase();
        try {
            Cursor cursor = db.rawQuery("SELECT * FROM TAIKHOAN", null);
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    User user = new User();
                    user.setMaTaiKhoan(cursor.getInt(0));
                    user.setTenDangNhap(cursor.getString(1));
                    user.setMatKhau(cursor.getString(2));
                    user.setHoTen(cursor.getString(3));
                    user.setEmail(cursor.getString(4));
                    user.setSoDienThoai(cursor.getString(5));
                    user.setDiaChi(cursor.getString(6));
                    user.setSoTien(cursor.getInt(7));
                    user.setLoaiTaiKhoan(cursor.getString(8));
                    user.setAnhnguoidung(cursor.getString(9));
                    list.add(user);
                    cursor.moveToNext();
                }

            }
        } catch (Exception e) {
            Log.i(TAG, "Lỗi", e);
        }

        return list;
    }

    public boolean checkDangNhap(String tenDangNhap, String matKhau) {
        Log.d(TAG, "CheckDangNhap: " + tenDangNhap + " - " + matKhau);
        SQLiteDatabase database = dbVnua.getReadableDatabase();
        try {
            Cursor cursor = database.rawQuery("SELECT * FROM TAIKHOAN WHERE tendangnhap = ? AND matkhau = ?", new String[]{tenDangNhap, matKhau});

            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                editor = sharedPreferences.edit();
                editor.putInt("mataikhoan", cursor.getInt(0));
                editor.putString("tendangnhap", cursor.getString(1));
                editor.putString("matkhau", cursor.getString(2));
                editor.putString("hoten", cursor.getString(3));
                editor.putString("email", cursor.getString(4));
                editor.putString("diachi", cursor.getString(5));
                editor.putInt("sotien", cursor.getInt(6));
                editor.putString("loaitaikhoan", cursor.getString(7));
                editor.apply();
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            Log.e(TAG, "Lỗi kiểm tra đăng nhập", e);
            return false;
        }
    }

    public boolean checkDangKy(User user) {
        SQLiteDatabase db = dbVnua.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tendangnhap", user.getTenDangNhap());
        values.put("matkhau", user.getMatKhau());
        values.put("hoten", user.getHoTen());
        values.put("email", user.getEmail());
        values.put("diachi", user.getDiaChi());
        values.put("sotien", user.getSoTien());
        values.put("loaitaikhoan", user.getLoaiTaiKhoan());
        long result = db.insert("TAIKHOAN", null, values);
        return result != -1;
    }

    public boolean tenDangNhapDaTonTai(String tenDangNhap) {
        SQLiteDatabase db = dbVnua.getReadableDatabase();
        String query = "SELECT * FROM TAIKHOAN WHERE tendangnhap =?";
        Cursor cursor = db.rawQuery(query, new String[]{tenDangNhap});
        boolean tonTai = cursor.getCount() > 0;
        cursor.close();
        return tonTai;
    }

    public int xoaNguoiDung(int mand) {
        SQLiteDatabase database = dbVnua.getWritableDatabase();
        Cursor cursor = database.rawQuery("Select * from DONHANG where mataikhoan = ?", new String[]{String.valueOf(mand)});
        if (cursor.getCount() != 0) {
            return -1;
        }

        long check = database.delete("TAIKHOAN", "mataikhoan = ?", new String[]{String.valueOf(mand)});
        if (check == -1) {
            return 0;
        } else {
            return 1;
        }
    }

    public boolean update(int manguoidung, String tennguoidung, int sotien) {
        SQLiteDatabase db = dbVnua.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("hoten", tennguoidung);
        values.put("sotien", sotien);
        long check = db.update("TAIKHOAN", values, "mataikhoan = ?", new String[]{String.valueOf(manguoidung)});
        if (check == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean updatekUser(User nguoiDung) {
        SQLiteDatabase db = dbVnua.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("anhtaikhoan", nguoiDung.getAnhnguoidung());
        values.put("hoten", nguoiDung.getHoTen());
        values.put("tendangnhap", nguoiDung.getTenDangNhap());
        values.put("matkhau", nguoiDung.getMatKhau());
        values.put("email", nguoiDung.getEmail());
        values.put("diachi", nguoiDung.getDiaChi());
        long check = db.update("TAIKHOAN", values, "mataikhoan = ?", new String[]{String.valueOf(nguoiDung.getMaTaiKhoan())});
        if (check == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean updateSoTien(int maTaiKhoan, int soTienMoi) {
        SQLiteDatabase db = dbVnua.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("sotien", soTienMoi);

        long result = db.update("TAIKHOAN", values, "mataikhoan = ?", new String[]{String.valueOf(maTaiKhoan)});

        return result != -1;
    }

    public User getNguoiDungByMaTaiKhoan(int maTaiKhoan) {
        SQLiteDatabase db = dbVnua.getReadableDatabase();
        User nguoiDung = null;

        try {
            Cursor cursor = db.rawQuery("SELECT * FROM TAIKHOAN WHERE mataikhoan = ?", new String[]{String.valueOf(maTaiKhoan)});

            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                nguoiDung = new User();
                nguoiDung.setMaTaiKhoan(cursor.getInt(0));
                nguoiDung.setTenDangNhap(cursor.getString(1));
                nguoiDung.setMatKhau(cursor.getString(2));
                nguoiDung.setEmail(cursor.getString(3));
                nguoiDung.setDiaChi(cursor.getString(4));
                nguoiDung.setSoTien(cursor.getInt(5));
                nguoiDung.setLoaiTaiKhoan(cursor.getString(6));
            }

            cursor.close();
        } catch (Exception e) {
            Log.e(TAG, "Lỗi", e);
        }

        return nguoiDung;
    }

}
