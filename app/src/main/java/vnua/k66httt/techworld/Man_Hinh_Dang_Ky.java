package vnua.k66httt.techworld;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import vnua.k66httt.techworld.Dao.UserDao;
import vnua.k66httt.techworld.Model.User;
import vnua.k66httt.techworld.databinding.ActivityManHinhRegisterBinding;

public class Man_Hinh_Dang_Ky extends AppCompatActivity {
    ActivityManHinhRegisterBinding binding;
    User user = new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_register);
        binding = ActivityManHinhRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.imgTroVeDangNhap.setOnClickListener(view -> {
            Intent intent = new Intent(Man_Hinh_Dang_Ky.this, Man_Hinh_Login.class);
            startActivity(intent);
        });
        binding.btnRegister.setOnClickListener(view -> {
            if (validateDangKy()) {
                clickDangKy();
            }

        });
    }

    private void clickDangKy() {
        // Lấy thông tin từ các trường nhập liệu
//        to do
        user.setLoaiTaiKhoan("khachhang"); // Đặt loại tài khoản mặc định khi đăng ký

        // Thực hiện đăng ký bằng cách thêm người dùng vào cơ sở dữ liệu
        UserDao dao = new UserDao(Man_Hinh_Dang_Ky.this);
        boolean result = dao.checkDangKy(user);

        if (result) {
            // Đăng ký thành công
            Intent intent = new Intent(Man_Hinh_Dang_Ky.this, Man_Hinh_Login.class);
            startActivity(intent);
        } else {
            // Đăng ký thất bại
            Toast.makeText(Man_Hinh_Dang_Ky.this, "Đăng ký thất bại", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean validateDangKy() {
        UserDao dao = new UserDao(Man_Hinh_Dang_Ky.this);
        String tenDangNhap = binding.edtTenDangNhapDangKy.getText().toString().trim();
        String matKhau = binding.edtNhapPassDangKy.getText().toString().trim();
        String nhapLaiMatKhau = binding.edtNhapLaiPassDangKy.getText().toString().trim();
        String diaChi = binding.edtNhapDiaChiDangKy.getText().toString().trim();
        String email = binding.edtNhapEmailDangKy.getText().toString().trim();
        boolean isValid = true;
        if (tenDangNhap.isEmpty()) {
            binding.tipLTenDangNhapDangKy.setError("Vui lòng nhập email/ tên đăng nhập");
            isValid = false;
        } else if (dao.tenDangNhapDaTonTai(tenDangNhap)) {
            binding.tipLTenDangNhapDangKy.setError("Email/ Tên đăng nhập đã tồn tại, vui lòng chọn tên khác");
            return false;
        } else {
            binding.tipLTenDangNhapDangKy.setError(null);
        }

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

        return isValid;

    }

    // Hàm kiểm tra định dạng email
    private boolean isValidEmail(String email) {
        String regex = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+(\\.+[a-z]+)?";
        return email.matches(regex);
    }
}