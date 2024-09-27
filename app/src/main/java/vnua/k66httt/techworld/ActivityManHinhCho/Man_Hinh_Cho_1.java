package vnua.k66httt.techworld.ActivityManHinhCho;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;

import vnua.k66httt.techworld.R;
import vnua.k66httt.techworld.databinding.ActivityManHinhCho1Binding;

public class Man_Hinh_Cho_1 extends AppCompatActivity {
    ActivityManHinhCho1Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_cho_1);
        binding = ActivityManHinhCho1Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btnTiepTucMh1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Man_Hinh_Cho_1.this, Man_Hinh_Cho_2.class);

                // Tạo hiệu ứng chuyển màn hình từ trái sang phải
                ActivityOptionsCompat options = ActivityOptionsCompat.makeCustomAnimation(
                        Man_Hinh_Cho_1.this, R.anim.slide_in_right, 0);

                // Bắt đầu Activity mới với hiệu ứng chuyển màn hình
                startActivity(intent, options.toBundle());
            }
        });

    }
}
