package vnua.k66httt.techworld;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import vnua.k66httt.techworld.ActivityManHinhCho.ActivityManHinhCho1;
import vnua.k66httt.techworld.Utils.PreferenceUtils;

public class ManHinhCho extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_cho_1);
        Handler handler = new Handler();
        if (PreferenceUtils.isFirstRun(this)) {
            // Nếu là lần đầu, hiển thị 3 màn hình

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(ManHinhCho.this, ActivityManHinhCho1.class);
                    startActivity(intent);
                    finish();
                }
            }, 1000);
        } else {

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(ManHinhCho.this, ManHinhLogin.class);
                    startActivity(intent);
                    finish();
                }
            }, 1000);
        }



    }

}
