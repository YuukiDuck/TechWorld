package vnua.k66httt.techworld;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import vnua.k66httt.techworld.ActivityManHinhCho.Man_Hinh_Cho_1;
import vnua.k66httt.techworld.Utils.PreferenceUtils;

public class man_hinh_cho extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_cho_1);
        Handler handler = new Handler();
        if (PreferenceUtils.isFirstRun(this)) {
            // Nếu là lần đầu, hiển thị 3 màn hình chờ
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(man_hinh_cho.this, Man_Hinh_Cho_1.class);
                    startActivity(intent);
                    finish();
                }
            }, 1000);
        } else {
            // Nếu không phải lần đầu, chuyển sang màn hình đăng nhập
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(man_hinh_cho.this, man_hinh_dang_nhap.class);
                    startActivity(intent);
                    finish();
                }
            }, 1000);
        }
    }
}
