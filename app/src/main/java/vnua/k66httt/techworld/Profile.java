package vnua.k66httt.techworld;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import vnua.k66httt.techworld.databinding.ActivityProfileBinding;

public class Profile extends AppCompatActivity {
    ActivityProfileBinding binding;
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        SharedPreferences preferences = getSharedPreferences("NGUOIDUNG", MODE_PRIVATE);
        int mand = preferences.getInt("mataikhoan", 0);
        String tenDN = preferences.getString("tendangnhap", "");
        String matkhau = preferences.getString("matkhau", "");
        String hoten = preferences.getString("hoten", "");
        String email = preferences.getString("email", "");
        String sodienthoai = preferences.getString("sodienthoai", "");
        String diachi = preferences.getString("diachi", "");
        int tien = preferences.getInt("sotien", 0);
        String loaitaikhoan = preferences.getString("loaitaikhoan", "");
        binding.hiName.setText("Hi" + hoten);
        binding.txtPMaNguoiDung.setText("Mẫ tài khoản:" + String.valueOf(mand));
        binding.txtPHoTen.setText("Họ tên:" + hoten);
        binding.txtPSoDienThoai.setText("Số điện thoại: " + sodienthoai);
        binding.txtPDiaChi.setText("Địa chỉ: " + diachi);
        binding.txtPSoTien.setText("Số tiền hiện có: " + String.valueOf(tien));
        binding.txtPLoaiTaiKhoan.setText("Loại tài khoản: " + loaitaikhoan);
        binding.btnPDangXuat.setOnClickListener(view -> {
                    Profile.this.startActivity(new Intent(Profile.this, man_hinh_dang_nhap.class));
//            getOnBackPressedDispatcher().onBackPressed();
                }
        );
        binding.imgBack.setOnClickListener(new View.OnClickListener() {
                                               @Override
                                               public void onClick(View view) {
//                                                   Profile.this.getOnBackPressedDispatcher().onBackPressed();
                                                   Profile.this.startActivity(new Intent(Profile.this, MainActivity.class));
                                               }
                                           }

        );

        binding.btnhoso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Profile.this, sua_thong_tin_nguoi_dung.class));
            }
        });
    }

}