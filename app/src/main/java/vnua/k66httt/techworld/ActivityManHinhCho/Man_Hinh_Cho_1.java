package vnua.k66httt.techworld.ActivityManHinhCho;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import vnua.k66httt.techworld.R;
import vnua.k66httt.techworld.databinding.ActivityManHinhCho1Binding;

public class Man_Hinh_Cho_1 extends AppCompatActivity {
    ActivityManHinhCho1Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityManHinhCho1Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Chuyển màn hình sang Man_Hinh_Cho_2 sau 1 giây
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Man_Hinh_Cho_1.this, Man_Hinh_Cho_2.class);
                ActivityOptionsCompat options = ActivityOptionsCompat.makeCustomAnimation(
                        Man_Hinh_Cho_1.this, R.anim.slide_in_right, 0);
                startActivity(intent, options.toBundle());
                finish();
            }
        }, 1000); // 1 giây
    }
}
