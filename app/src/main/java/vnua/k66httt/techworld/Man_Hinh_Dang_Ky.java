package vnua.k66httt.techworld;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import vnua.k66httt.techworld.Dao.UserDao;
import vnua.k66httt.techworld.Model.User;
import vnua.k66httt.techworld.databinding.ActivityManHinhRegisterBinding;

public class Man_Hinh_Dang_Ky extends AppCompatActivity {
    ActivityManHinhRegisterBinding binding;
    User user  = new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityManHinhRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.imgTroVeDangNhap.setOnClickListener(view -> {
            Intent intent = new Intent(Man_Hinh_Dang_Ky.this, Man_Hinh_Login.class);
            startActivity(intent);
        });

        binding.btnDongY.setOnClickListener(view -> {
            if (validateDangKy()) {
                clickDangKy();
            }
        });
    }

    private void clickDangKy() {
        // Lấy thông tin từ các trường nhập liệu
        user.setMatKhau(binding.edtNhapPassDangKy.getText().toString().trim());
        user.setHoTen(binding.edtNhapHoTenDangKy.getText().toString());
        user.setSoDienThoai(binding.edtNhapSDTDangKy.getText().toString().trim());
        user.setDiaChi(binding.edtNhapDiaChiDangKy.getText().toString());
        user.setEmail(binding.edtNhapEmailDangKy.getText().toString().trim());
        user.setSoTien(0); // Đặt số tiền mặc định khi đăng ký
        user.setLoaiTaiKhoan("khachhang"); // Đặt loại tài khoản mặc định khi đăng ký
            user.setGioiTinh(binding.rgGioiTinh.getCheckedRadioButtonId() == R.id.rbnam ? "Nam" : "Nữ");

        // Thực hiện đăng ký bằng cách thêm người dùng vào cơ sở dữ liệu
        UserDao dao = new UserDao(Man_Hinh_Dang_Ky.this);
        boolean result = dao.checkDangKy(user);

        if (result) {
            // Đăng ký thành công
            Intent intent = new Intent(Man_Hinh_Dang_Ky.this, Man_Hinh_Login.class);
            startActivity(intent);
            finish();
        } else {
            // Đăng ký thất bại
            Toast.makeText(Man_Hinh_Dang_Ky.this, "Đăng ký thất bại", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean validateDangKy() {
        UserDao dao = new UserDao(Man_Hinh_Dang_Ky.this);
        String matKhau = binding.edtNhapPassDangKy.getText().toString().trim();
        String nhapLaiMatKhau = binding.edtNhapLaiPassDangKy.getText().toString().trim();
        String hoTen = binding.edtNhapHoTenDangKy.getText().toString().trim();
        String sdt = binding.edtNhapSDTDangKy.getText().toString().trim();
        String diaChi = binding.edtNhapDiaChiDangKy.getText().toString().trim();
        String email = binding.edtNhapEmailDangKy.getText().toString().trim();
        String gioiTinh = binding.rgGioiTinh.getCheckedRadioButtonId() == -1 ? null : "đã chọn"; // Kiểm tra giới tính
        boolean isValid = true;

        if (matKhau.isEmpty()) {
            binding.tipLNhapPassDangKy.setError("Vui lòng nhập mật khẩu");
            isValid = false;
        } else {
            binding.tipLNhapPassDangKy.setError(null);
        }

        if (nhapLaiMatKhau.isEmpty()) {
            binding.tiLNhapLaiPassDangKy.setError("Vui lòng nhập lại mật khẩu");
            isValid = false;
        } else if (!nhapLaiMatKhau.equals(matKhau)) {
            binding.tiLNhapLaiPassDangKy.setError("Mật khẩu không trùng nhau");
            isValid = false;
        } else {
            binding.tiLNhapLaiPassDangKy.setError(null);
        }

        if (hoTen.isEmpty()) {
            binding.tiLNhapHoTenDangKy.setError("Vui lòng nhập họ tên");
            isValid = false;
        } else {
            binding.tiLNhapHoTenDangKy.setError(null);
        }

        if (sdt.isEmpty()) {
            binding.tiLNhapSDTDangKy.setError("Vui lòng nhập số điện thoại");
            isValid = false;
        } else if (!isValidPhoneNumber(sdt)) {
            binding.tiLNhapSDTDangKy.setError("Số điện thoại không hợp lệ");
            isValid = false;
        } else {
            binding.tiLNhapSDTDangKy.setError(null);
        }

        if (diaChi.isEmpty()) {
            binding.tiLNhapDiaChiDangKy.setError("Vui lòng nhập địa chỉ");
            isValid = false;
        } else {
            binding.tiLNhapDiaChiDangKy.setError(null);
        }

        if (email.isEmpty()) {
            binding.tiLNhapEmailDangKy.setError("Vui lòng nhập email");
            isValid = false;
        } else if (!isValidEmail(email)) {
            binding.tiLNhapEmailDangKy.setError("Email không hợp lệ");
            isValid = false;
        } else {
            binding.tiLNhapEmailDangKy.setError(null);
        }

        if (gioiTinh == null) {
            Toast.makeText(this, "Vui lòng chọn giới tính", Toast.LENGTH_SHORT).show();
            isValid = false;
        }

        return isValid;
    }

    private boolean isValidPhoneNumber(String phoneNumber) {
        String regex = "0\\d{9}";
        return phoneNumber.matches(regex);
    }

    private boolean isValidEmail(String email) {
        String regex = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+(\\.+[a-z]+)?";
        return email.matches(regex);
    }
}