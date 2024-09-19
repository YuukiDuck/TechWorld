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

public class Man_Hinh_Login extends AppCompatActivity {
    ActivityManHinhLoginBinding binding;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_login);
        binding = ActivityManHinhLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        checkRemember();
        UserDao userDao = new UserDao(this);
        Animation animation = AnimationUtils.loadAnimation(this,R.anim.anim_button);
        binding.btnDangNhap.setAnimation(animation);
        binding.edtEmailDangNhap.setImeOptions(EditorInfo.IME_ACTION_NEXT);
        binding.edtMatKhau.setImeOptions(EditorInfo.IME_ACTION_DONE);
        binding.btnDangNhap.setOnClickListener(view -> {
            view.startAnimation(animation);
            String userName = binding.edtEmailDangNhap.getText().toString();
            String passWord = binding.edtMatKhau.getText().toString();

            if (userName.isEmpty()) {
                binding.edtEmailDangNhap.setError("Không được để trống");

            }
            if (passWord.isEmpty()) {
                binding.edtMatKhau.setError("Không được để trống");
                return;
            }
            if (userDao.checkDangNhap(userName, passWord)) {
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
                Intent intent = new Intent(Man_Hinh_Login.this, MainActivity.class);
                Man_Hinh_Login.this.startActivity(intent);
                this.finish();
            } else {
                binding.edtEmailDangNhap.setError("Email đăng nhập không đúng");
                binding.edtMatKhau.setError("Mật khẩu không đúng");
            }

        });

        binding.txtChuyenQuaDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Man_Hinh_Login.this, Man_Hinh_Dang_Ky.class);
                startActivity(intent);
            }
        });
    }

    private void checkRemember() {
        preferences = this.getSharedPreferences("ACCOUNT", MODE_PRIVATE);
        editor = preferences.edit(); // gọi dòng trên và edit vào nó
        boolean isCheck = preferences.getBoolean("isChecked", false);
        if (isCheck) {
            binding.edtEmailDangNhap.setText(preferences.getString("username", ""));
            binding.edtMatKhau.setText(preferences.getString("password", ""));
            binding.chkNhoMatKhau.setChecked(isCheck);
        }
    }
}