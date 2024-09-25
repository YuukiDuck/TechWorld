package vnua.k66httt.techworld;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import vnua.k66httt.techworld.Dao.UserDao;
import vnua.k66httt.techworld.Model.User;
import vnua.k66httt.techworld.databinding.ActivityEditUserBinding;

public class EditUser extends AppCompatActivity {
    ActivityEditUserBinding binding;
    UserDao dao;
    User user;
    private ArrayList<User> list = new ArrayList<>();
    String email, matkhaucu, matkhaumoi, nhaplaimatkhaumoi, hoten, diachi, sodienthoai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);
        binding = ActivityEditUserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        SharedPreferences preferences = getSharedPreferences("NGUOIDUNG", MODE_PRIVATE);
        int maTK = preferences.getInt("mataikhoan",0);
        String matkhau = preferences.getString("matkhau","");
        String hoten = preferences.getString("hoten","");
        String email = preferences.getString("email","");
        String sodienthoai = preferences.getString("sodienthoai","");
        String diachi = preferences.getString("diachi","");
        dao = new UserDao(this);
        user = dao.getUserByMaTaiKhoan(maTK);
        binding.edtNhapDiaChiDangKy.setText(diachi);
        binding.edtNhapEmailDangKy.setText(email);
        binding.edtTenDangNhapDangKy.setText(hoten);
    }
}
