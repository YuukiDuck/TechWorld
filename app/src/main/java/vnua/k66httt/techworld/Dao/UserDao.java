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

    public boolean checkLogin(String userIdentifier, String password) {
        Log.d(TAG, "CheckLogin: " + userIdentifier + " - " + password);
        SQLiteDatabase database = dbVnua.getReadableDatabase();
        try {
            Cursor cursor = database.rawQuery("SELECT * FROM TAIKHOAN WHERE (email = ? OR sodienthoai = ?) AND matkhau = ?",
                    new String[]{userIdentifier, userIdentifier, password});
            return cursor.getCount() > 0;
        } catch (Exception e) {
            Log.e(TAG, "Lỗi kiểm tra đăng nhập", e);
            return false;
        }
    }

    public boolean checkDangKy(User user) {
        SQLiteDatabase db = dbVnua.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("matkhau", user.getMatKhau());
        values.put("hoten", user.getHoTen());
        values.put("email", user.getEmail());
        values.put("sodienthoai", user.getSoDienThoai().toString());
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
        cursor.close();
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

    public int deleteUser(int maTaiKhoan) {
        SQLiteDatabase database = dbVnua.getWritableDatabase();
        long result = database.delete("TAIKHOAN", "mataikhoan = ?", new String[]{String.valueOf(maTaiKhoan)});
        return result != -1 ? 1 : 0;
    }
}
