package vnua.k66httt.techworld;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;

import androidx.appcompat.app.AppCompatActivity;

import vnua.k66httt.techworld.Dao.UserDao;
import vnua.k66httt.techworld.databinding.ActivityManHinhLoginBinding;

public class Man_Hinh_Dang_Ky extends AppCompatActivity {
    ActivityManHinhLoginBinding binding;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityManHinhLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        checkRemember();
        UserDao userDao = new UserDao(this);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_button);
        binding.btnDangNhap.setAnimation(animation);
        binding.edtEmailDangNhap.setImeOptions(EditorInfo.IME_ACTION_NEXT);
        binding.edtMatKhau.setImeOptions(EditorInfo.IME_ACTION_DONE);

        binding.btnDangNhap.setOnClickListener(view -> {
            view.startAnimation(animation);
            String userName = binding.edtEmailDangNhap.getText().toString();
            String passWord = binding.edtMatKhau.getText().toString();

            if (userName.isEmpty()) {
                binding.edtEmailDangNhap.setError("Không được để trống");
                return;
            }
            if (passWord.isEmpty()) {
                binding.edtMatKhau.setError("Không được để trống");
                return;
            }
            if (userDao.checkLogin(userName, passWord)) {
                if (binding.chkNhoMatKhau.isChecked()) {
                    editor.putString("username", userName);
                    editor.putString("password", passWord);
                    editor.putBoolean("isChecked", binding.chkNhoMatKhau.isChecked());
                    editor.apply();
                } else {
                    editor.clear();
                    editor.apply();
                }
                binding.edtEmailDangNhap.setText("");
                binding.edtMatKhau.setText("");
                Intent intent = new Intent(Man_Hinh_Dang_Ky.this, MainActivity.class);
                startActivity(intent);
                finish();
            } else {
                binding.edtEmailDangNhap.setError("Email hoặc số điện thoại đăng nhập không đúng");
                binding.edtMatKhau.setError("Mật khẩu không đúng");
            }
        });

        binding.txtChuyenQuaDangKy.setOnClickListener(view -> {
            Intent intent = new Intent(Man_Hinh_Dang_Ky.this, Man_Hinh_Dang_Ky.class);
            startActivity(intent);
        });
    }

    private void checkRemember() {
        preferences = this.getSharedPreferences("ACCOUNT", MODE_PRIVATE);
        editor = preferences.edit();
        boolean isCheck = preferences.getBoolean("isChecked", false);
        if (isCheck) {
            binding.edtEmailDangNhap.setText(preferences.getString("username", ""));
            binding.edtMatKhau.setText(preferences.getString("password", ""));
            binding.chkNhoMatKhau.setChecked(isCheck);
        }
    }
}
