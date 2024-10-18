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
import vnua.k66httt.techworld.databinding.ActivitySuaThongTinNguoiDungBinding;

public class sua_thong_tin_nguoi_dung extends AppCompatActivity {
    ActivitySuaThongTinNguoiDungBinding binding;
    UserDao dao;
    User user;
    private ArrayList<User> list = new ArrayList<>();
    String email, matkhaucu, matkhaumoi, nhaplaimatkhaumoi, hoten, diachi, sodienthoai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySuaThongTinNguoiDungBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SharedPreferences preferences = getSharedPreferences("NGUOIDUNG", MODE_PRIVATE);
        int maTK = preferences.getInt("mataikhoan", 0);
        dao = new UserDao(this);

        // Lấy thông tin người dùng
        user = dao.getUserByMaTaiKhoan(maTK);
        if (user != null) {
            binding.edtNhapHoTen.setText(user.getHoTen());
            binding.edtNhapDiaChiDangKy.setText(user.getDiaChi());
            binding.edtNhapEmailDangKy.setText(user.getEmail());
            binding.edtNhapSDT.setText(user.getSoDienThoai());
        } else {
            Toast.makeText(this, "Không tìm thấy người dùng", Toast.LENGTH_SHORT).show();
            finish(); // Kết thúc activity nếu không tìm thấy người dùng
            return;
        }

        binding.imgTroVeDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(sua_thong_tin_nguoi_dung.this, Profile.class));
            }
        });

        binding.btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validHoTen();
                validDiaChi();
                validEmail();
                validSoDienThoai();
                validMatKhauCu();
                validNhapLaiMatKhauMoi();

                if (isInputValid()) {
                    user.setMatKhau(matkhaumoi);
                    user.setHoTen(hoten);
                    user.setEmail(email);
                    user.setDiaChi(diachi);
                    user.setSoDienThoai(sodienthoai);

                    boolean result = dao.updatekhachhang(user);
                    if (result) {
                        list.clear();
                        list = dao.getAllUsers();
                        Intent intent = new Intent(sua_thong_tin_nguoi_dung.this, man_hinh_dang_nhap.class);
                        startActivity(intent);
                        Toast.makeText(sua_thong_tin_nguoi_dung.this, "Đổi thông tin thành công", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(sua_thong_tin_nguoi_dung.this, "Đổi thông tin thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private boolean isInputValid() {
        return binding.edmatKhau.getError() == null &&
                binding.edmatKhauMoi.getError() == null &&
                binding.edtNhapLaiPassMoi.getError() == null &&
                binding.edtNhapHoTen.getError() == null &&
                binding.edtNhapEmailDangKy.getError() == null &&
                binding.edtNhapDiaChiDangKy.getError() == null &&
                binding.edtNhapSDT.getError() == null;
    }

    private void validHoTen() {
        hoten = binding.edtNhapHoTen.getText().toString();
        if (hoten.isEmpty()) {
            binding.edtNhapHoTen.setError("Vui lòng nhập họ tên");
        } else {
            binding.edtNhapHoTen.setError(null);
        }
    }

    private void validDiaChi() {
        diachi = binding.edtNhapDiaChiDangKy.getText().toString();
        if (diachi.isEmpty()) {
            binding.edtNhapDiaChiDangKy.setError("Vui lòng nhập địa chỉ");
        } else {
            binding.edtNhapDiaChiDangKy.setError(null);
        }
    }

    private void validEmail() {
        email = binding.edtNhapEmailDangKy.getText().toString();
        if (email.isEmpty()) {
            binding.edtNhapEmailDangKy.setError("Vui lòng nhập email");
        } else if (!isValidEmail(email)) {
            binding.edtNhapEmailDangKy.setError("Email không hợp lệ");
        } else {
            binding.edtNhapEmailDangKy.setError(null);
        }
    }

    private void validSoDienThoai() {
        sodienthoai = binding.edtNhapSDT.getText().toString();
        if (sodienthoai.isEmpty()) {
            binding.edtNhapSDT.setError("Vui lòng nhập số điện thoại");
        } else if (!isValidPhoneNumber(sodienthoai)) {
            binding.edtNhapSDT.setError("Số điện thoại không hợp lệ");
        } else {
            binding.edtNhapSDT.setError(null);
        }
    }

    private void validMatKhauCu() {
        matkhaucu = binding.edmatKhau.getText().toString();
        if (matkhaucu.isEmpty()) {
            binding.edmatKhau.setError("Vui lòng nhập lại mật khẩu");
        } else if (!matkhaucu.equals(user.getMatKhau())) { // Sửa dòng này để so sánh với mật khẩu trong đối tượng user
            binding.edmatKhau.setError("Mật khẩu không đúng");
        } else {
            binding.edmatKhau.setError(null);
        }
    }

    private void validNhapLaiMatKhauMoi() {
        nhaplaimatkhaumoi = binding.edtNhapLaiPassMoi.getText().toString();
        matkhaumoi = binding.edmatKhauMoi.getText().toString();
        if (nhaplaimatkhaumoi.isEmpty()) {
            binding.edtNhapLaiPassMoi.setError("Vui lòng nhập lại mật khẩu");
        } else if (matkhaumoi.isEmpty()) {
            binding.edmatKhauMoi.setError("Vui lòng nhập mật khẩu mới");
        } else if (!nhaplaimatkhaumoi.equals(matkhaumoi)) {
            binding.edtNhapLaiPassMoi.setError("Mật khẩu không trùng nhau");
        } else {
            binding.edtNhapLaiPassMoi.setError(null);
            binding.edmatKhauMoi.setError(null);
        }
    }

    private boolean isValidPhoneNumber(String phoneNumber) {
        String regex = "0\\d{9}";
        return phoneNumber.matches(regex);
    }

    private boolean isValidEmail(String email) {
        String regex = "[a-zA-Z0-9._-]+@gmail\\.com";
        return email.matches(regex);
    }
}