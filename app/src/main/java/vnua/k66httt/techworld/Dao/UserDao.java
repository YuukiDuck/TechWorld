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

    public boolean checkDangNhap(String userIdentifier, String password) {
        Log.d(TAG, "CheckLogin: " + userIdentifier + " - " + password);
        SQLiteDatabase database = dbVnua.getReadableDatabase();
        try (Cursor cursor = database.rawQuery("SELECT * FROM TAIKHOAN WHERE (email = ? OR sodienthoai = ?) AND matkhau = ?",
                new String[]{userIdentifier, userIdentifier, password})) {
            return cursor.getCount() > 0;
        } catch (Exception e) {
            Log.e(TAG, "Lỗi kiểm tra đăng nhập", e);
            return false;
        }
    }


    public boolean checkDangKy(User user) {
        // Kiểm tra nếu email hoặc số điện thoại đã tồn tại
        if (emailOrPhoneExists(user.getEmail()) || emailOrPhoneExists(user.getSoDienThoai())) {
            return false; // Người dùng đã tồn tại
        }

        SQLiteDatabase db = dbVnua.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("matkhau", user.getMatKhau());
        values.put("hoten", user.getHoTen());
        values.put("email", user.getEmail());
        values.put("sodienthoai", user.getSoDienThoai());
        values.put("diachi", user.getDiaChi());
        values.put("sotien", user.getSoTien());
        values.put("loaitaikhoan", user.getLoaiTaiKhoan());

        long result = db.insert("TAIKHOAN", null, values);
        return result != -1;
    }


    public boolean emailOrPhoneExists(String identifier) {
        SQLiteDatabase db = dbVnua.getReadableDatabase();
        String query = "SELECT * FROM TAIKHOAN WHERE email = ? OR sodienthoai = ?";
        Cursor cursor = db.rawQuery(query, new String[]{identifier, identifier});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }

    public ArrayList<User> getAllUsers() {
        ArrayList<User> list = new ArrayList<>();
        SQLiteDatabase db = dbVnua.getReadableDatabase();
        String sql = "SELECT * FROM TAIKHOAN";

        try (Cursor cursor = db.rawQuery(sql, null)) {
            if (cursor.moveToFirst()) {
                do {
                    User user = new User();
                    user.setMaTaiKhoan(cursor.getInt(0));
                    user.setHoTen(cursor.getString(1));
                    user.setMatKhau(cursor.getString(2));
                    user.setEmail(cursor.getString(3));
                    user.setSoDienThoai(cursor.getString(4));
                    user.setDiaChi(cursor.getString(5));
                    user.setSoTien(cursor.getInt(6));
                    user.setLoaiTaiKhoan(cursor.getString(7));
                    list.add(user);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e(TAG, "Lỗi khi lấy tất cả người dùng", e);
        }
        return list;
    }

    public boolean updateUser(User user) {
        SQLiteDatabase db = dbVnua.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("hoten", user.getHoTen());
        values.put("sodienthoai", user.getSoDienThoai());
        values.put("matkhau", user.getMatKhau());
        values.put("email", user.getEmail());
        values.put("diachi", user.getDiaChi());

        long result = db.update("TAIKHOAN", values, "mataikhoan = ?", new String[]{String.valueOf(user.getMaTaiKhoan())});
        return result != -1;
    }

    public User getUserByMaTaiKhoan(int maTaiKhoan) {
        SQLiteDatabase db = dbVnua.getReadableDatabase();
        User user = null;
        try {
            Cursor cursor = db.rawQuery("SELECT * FROM TAIKHOAN WHERE mataikhoan = ?", new String[]{String.valueOf(maTaiKhoan)});

            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                user = new User();
                user.setMaTaiKhoan(cursor.getInt(0));
                user.setMatKhau(cursor.getString(1));
                user.setHoTen(cursor.getString(2));
                user.setEmail(cursor.getString(3));
                user.setSoDienThoai(cursor.getString(4));
                user.setDiaChi(cursor.getString(5));
                user.setSoTien(cursor.getInt(6));
                user.setLoaiTaiKhoan(cursor.getString(7));
            }

            cursor.close();
        } catch (Exception e) {
            Log.e(TAG, "Lỗi", e);
        }
        return user;
    }

    public boolean updatekhachhang(User user) {
        SQLiteDatabase db = dbVnua.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("hoten", user.getHoTen());
        values.put("sodienthoai", user.getSoDienThoai());
        values.put("matkhau", user.getMatKhau());
        values.put("email", user.getEmail());
        values.put("diachi", user.getDiaChi());
        long check = db.update("TAIKHOAN", values, "mataikhoan = ?", new String[]{String.valueOf(user.getMaTaiKhoan())});
        if (check == -1) {
            return false;
        } else {
            return true;
        }
    }

    public int deleteUser(int maTaiKhoan) {
        SQLiteDatabase database = dbVnua.getWritableDatabase();
        long result = database.delete("TAIKHOAN", "mataikhoan = ?", new String[]{String.valueOf(maTaiKhoan)});
        return result != -1 ? 1 : 0;
    }
}
