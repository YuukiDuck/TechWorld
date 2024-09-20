package vnua.k66httt.techworld.Dao;

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
    private static final String TAG = "UserDao";

    public UserDao(Context context) {
        this.dbVnua = new dbVnua(context);
        if (context != null) {
            sharedPreferences = context.getSharedPreferences("USER", context.MODE_PRIVATE);
        } else {
            Log.e(TAG, "Context is null in UserDao constructor");
        }
    }

    public ArrayList<User> getAllUsers() {
        ArrayList<User> list = new ArrayList<>();
        SQLiteDatabase db = dbVnua.getReadableDatabase();
        try {
            Cursor cursor = db.rawQuery("SELECT * FROM TAIKHOAN", null);
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
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
                    cursor.moveToNext();
                }
            }
        } catch (Exception e) {
            Log.i(TAG, "Lỗi", e);
        }

        return list;
    }

    public boolean checkDangNhap(String userIdentifier, String password) {
        Log.d(TAG, "CheckLogin: " + userIdentifier + " - " + password);
        SQLiteDatabase database = dbVnua.getReadableDatabase();
        try {
            Cursor cursor = database.rawQuery("SELECT * FROM TAIKHOAN WHERE (tendangnhap = ? OR email = ? OR sodienthoai = ?) AND matkhau = ?",
                    new String[]{userIdentifier, userIdentifier, userIdentifier, password});
            return cursor.getCount() > 0;
        } catch (Exception e) {
            Log.e(TAG, "Lỗi kiểm tra đăng nhập", e);
            return false;
        }
    }

    public boolean checkDangKy(User user) {
        SQLiteDatabase db = dbVnua.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tendangnhap", user.getHoTen());
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

    public boolean usernameExists(String username) {
        SQLiteDatabase db = dbVnua.getReadableDatabase();
        String query = "SELECT * FROM TAIKHOAN WHERE tendangnhap = ?";
        Cursor cursor = db.rawQuery(query, new String[]{username});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }

    public int deleteUser(int userId) {
        SQLiteDatabase database = dbVnua.getWritableDatabase();
        Cursor cursor = database.rawQuery("Select * from DONHANG where mataikhoan = ?", new String[]{String.valueOf(userId)});
        if (cursor.getCount() != 0) {
            return -1;
        }
        long check = database.delete("TAIKHOAN", "mataikhoan = ?", new String[]{String.valueOf(userId)});
        return check == -1 ? 0 : 1;
    }

    public boolean updateUser(int userId, String username, int balance) {
        SQLiteDatabase db = dbVnua.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("hoten", username);
        values.put("sotien", balance);
        long check = db.update("TAIKHOAN", values, "mataikhoan = ?", new String[]{String.valueOf(userId)});
        return check != -1;
    }

    public boolean updateCustomer(User user) {
        SQLiteDatabase db = dbVnua.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("hoten", user.getHoTen());
        values.put("tendangnhap", user.getHoTen());
        values.put("sodienthoai", user.getSoDienThoai());
        values.put("matkhau", user.getMatKhau());
        values.put("email", user.getEmail());
        values.put("diachi", user.getDiaChi());
        long check = db.update("TAIKHOAN", values, "mataikhoan = ?", new String[]{String.valueOf(user.getMaTaiKhoan())});
        return check != -1;
    }

    public boolean updateBalance(int userId, int newBalance) {
        SQLiteDatabase db = dbVnua.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("sotien", newBalance);
        long result = db.update("TAIKHOAN", values, "mataikhoan = ?", new String[]{String.valueOf(userId)});
        return result != -1;
    }

    public User getUserById(int userId) {
        SQLiteDatabase db = dbVnua.getReadableDatabase();
        User user = null;
        try {
            Cursor cursor = db.rawQuery("SELECT * FROM TAIKHOAN WHERE mataikhoan = ?", new String[]{String.valueOf(userId)});
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                user = new User();
                user.setMaTaiKhoan(cursor.getInt(0));
                user.setHoTen(cursor.getString(1));
                user.setMatKhau(cursor.getString(2));
                user.setHoTen(cursor.getString(3));
                user.setEmail(cursor.getString(4));
                user.setSoDienThoai(cursor.getString(5));
                user.setDiaChi(cursor.getString(6));
                user.setSoTien(cursor.getInt(7));
                user.setLoaiTaiKhoan(cursor.getString(8));
            }
            cursor.close();
        } catch (Exception e) {
            Log.e(TAG, "Lỗi", e);
        }
        return user;
    }
}
