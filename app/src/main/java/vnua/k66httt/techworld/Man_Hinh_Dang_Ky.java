package vnua.k66httt.techworld;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import vnua.k66httt.techworld.Dao.UserDao;
import vnua.k66httt.techworld.Man_Hinh_Login;
import vnua.k66httt.techworld.Model.User;
import vnua.k66httt.techworld.databinding.ActivityManHinhRegisterBinding;

public class Man_Hinh_Dang_Ky extends AppCompatActivity {
    ActivityManHinhRegisterBinding binding;
    User user = new User();

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
        // Lấy thông tin từ các trường input
        user.setHoTen(binding.edtNhapHoTenDangKy.getText().toString().trim()); // Thêm họ tên
        user.setSoDienThoai(binding.edtNhapSDTDangKy.getText().toString().trim()); // Thêm số điện thoại
        user.setEmail(binding.edtNhapEmailDangKy.getText().toString().trim());
        user.setMatKhau(binding.edtNhapPassDangKy.getText().toString().trim());
        user.setDiaChi(binding.edtNhapDiaChiDangKy.getText().toString().trim());
        user.setLoaiTaiKhoan("khachhang"); // Đặt loại tài khoản mặc định khi đăng ký

        // Lấy giới tính từ RadioButton (đã validate thành công)
        int selectedGenderId = binding.rgGioiTinh.getCheckedRadioButtonId();
        RadioButton selectedGenderButton = findViewById(selectedGenderId);
        if (selectedGenderButton != null) {
            String gioiTinh = selectedGenderButton.getText().toString();
            user.setGioiTinh(gioiTinh); // Gán giới tính cho user
        }

        // Thực hiện đăng ký bằng cách thêm người dùng vào cơ sở dữ liệu
        UserDao dao = new UserDao(Man_Hinh_Dang_Ky.this);
        boolean result = dao.checkDangKy(user);

        if (result) {
            Intent intent = new Intent(Man_Hinh_Dang_Ky.this, Man_Hinh_Login.class);
            startActivity(intent);
        } else {
            Toast.makeText(Man_Hinh_Dang_Ky.this, "Đăng ký thất bại", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean validateDangKy() {
        UserDao dao = new UserDao(Man_Hinh_Dang_Ky.this);
        String matKhau = binding.edtNhapPassDangKy.getText().toString().trim();
        String nhapLaiMatKhau = binding.edtNhapLaiPassDangKy.getText().toString().trim();
        String diaChi = binding.edtNhapDiaChiDangKy.getText().toString().trim();
        String email = binding.edtNhapEmailDangKy.getText().toString().trim();
        String hoTen = binding.edtNhapHoTenDangKy.getText().toString().trim();
        String soDienThoai = binding.edtNhapSDTDangKy.getText().toString().trim();
        boolean isValid = true;

        // Kiểm tra giới tính
        int selectedGenderId = binding.rgGioiTinh.getCheckedRadioButtonId();
        if (selectedGenderId == -1) {
            Toast.makeText(this, "Vui lòng chọn giới tính", Toast.LENGTH_SHORT).show();
            isValid = false;
        }

        // Kiểm tra họ tên
        if (hoTen.isEmpty()) {
            binding.tiLNhapHoTenDangKy.setError("Vui lòng nhập họ tên");
            isValid = false;
        } else {
            binding.tiLNhapHoTenDangKy.setError(null);
        }

        // Kiểm tra số điện thoại
        if (soDienThoai.isEmpty()) {
            binding.tiLNhapSDTDangKy.setError("Vui lòng nhập số điện thoại");
            isValid = false;
        } else if (!isValidPhoneNumber(soDienThoai)) {
            binding.tiLNhapSDTDangKy.setError("Số điện thoại không hợp lệ");
            isValid = false;
        } else {
            binding.tiLNhapSDTDangKy.setError(null);
        }

        // Kiểm tra mật khẩu
        if (matKhau.isEmpty()) {
            binding.tipLNhapPassDangKy.setError("Vui lòng nhập mật khẩu");
            isValid = false;
        } else {
            binding.tipLNhapPassDangKy.setError(null);
        }

        // Kiểm tra nhập lại mật khẩu
        if (nhapLaiMatKhau.isEmpty()) {
            binding.tiLNhapLaiPassDangKy.setError("Vui lòng nhập lại mật khẩu");
            isValid = false;
        } else if (!nhapLaiMatKhau.equals(matKhau)) {
            binding.tiLNhapLaiPassDangKy.setError("Mật khẩu không trùng nhau");
            isValid = false;
        } else {
            binding.tiLNhapLaiPassDangKy.setError(null);
        }

        // Kiểm tra địa chỉ
        if (diaChi.isEmpty()) {
            binding.tiLNhapDiaChiDangKy.setError("Vui lòng nhập địa chỉ");
            isValid = false;
        } else {
            binding.tiLNhapDiaChiDangKy.setError(null);
        }

        // Kiểm tra email
        if (email.isEmpty()) {
            binding.tiLNhapEmailDangKy.setError("Vui lòng nhập email");
            isValid = false;
        } else if (!isValidEmail(email)) {
            binding.tiLNhapEmailDangKy.setError("Email không hợp lệ");
            isValid = false;
        } else {
            binding.tiLNhapEmailDangKy.setError(null);
        }

        return isValid;
    }

    private boolean isValidEmail(String email) {
        String regex = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+(\\.+[a-z]+)?";
        return email.matches(regex);
    }

    private boolean isValidPhoneNumber(String phoneNumber) {
        String regex = "^(\\+84|0)[0-9]{9,10}$";
        return phoneNumber.matches(regex);
    }
}