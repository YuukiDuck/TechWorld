package vnua.k66httt.techworld.ActivityManHinhCho;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import vnua.k66httt.techworld.man_hinh_dang_nhap;
import vnua.k66httt.techworld.R;
import vnua.k66httt.techworld.databinding.ActivityManHinhCho3Binding;

public class Man_Hinh_Cho_3 extends AppCompatActivity {
    ActivityManHinhCho3Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityManHinhCho3Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Chuyển sang màn hình đăng nhập sau 1 giây
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Man_Hinh_Cho_3.this, man_hinh_dang_nhap.class);
                ActivityOptionsCompat options = ActivityOptionsCompat.makeCustomAnimation(
                        Man_Hinh_Cho_3.this, R.anim.slide_in_right, 0);
                startActivity(intent, options.toBundle());
                finish();
            }
        }, 1000); // 1 giây
    }
}
