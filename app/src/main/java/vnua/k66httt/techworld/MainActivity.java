package vnua.k66httt.techworld;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import vnua.k66httt.techworld.databinding.ActivityMainBinding;
import vnua.k66httt.techworld.fragment.frgGianHang;
import vnua.k66httt.techworld.fragment.frgGioHang;
import vnua.k66httt.techworld.fragment.frgLichSuDonHang;
import vnua.k66httt.techworld.fragment.frgNapTien;
import vnua.k66httt.techworld.fragment.frgQuanLyDonHang;
import vnua.k66httt.techworld.fragment.frgQuanLyLoaiSanPham;
import vnua.k66httt.techworld.fragment.frgQuanLyNapTien;
import vnua.k66httt.techworld.fragment.frgQuanLyNguoiDung;
import vnua.k66httt.techworld.fragment.frgQuanLySanPham;
import vnua.k66httt.techworld.fragment.frgTrangChu;
import vnua.k66httt.techworld.fragment.frgVeChungToi;
import vnua.k66httt.techworld.fragment.frgThongKe;


public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        navigationView = binding.navigationViewMain;

        binding.navigationViewMain.getHeaderView(0);
        Toolbar toolbar = binding.toolbarMain;
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        actionBar.setTitle("TechShop");

        handleBottomNavigationItemSelected();
        if (savedInstanceState == null) {
            replaceFragment(new frgTrangChu());
            getSupportActionBar().setTitle("Trang chủ");
        }

        binding.btnGiohang.setOnClickListener(view ->
                replaceFragment(new frgGioHang())
        );

        navigationView.setNavigationItemSelectedListener(item -> {
            if (item.getItemId() == R.id.action_qlnguoidung) {
                replaceFragment(new frgQuanLyNguoiDung());
            } else if (item.getItemId() == R.id.action_qlsanpham) {
                replaceFragment(new frgQuanLySanPham());
            } else if (item.getItemId() == R.id.action_qlLoaisanpham) {
                replaceFragment(new frgQuanLyLoaiSanPham());
            } else if (item.getItemId() == R.id.action_qlDonHang) {
                replaceFragment(new frgQuanLyDonHang());
            } else if (item.getItemId() == R.id.action_qlNapTien) {
                replaceFragment(new frgQuanLyNapTien());
            } else if (item.getItemId() == R.id.action_qlThongKe) {
                replaceFragment(new frgThongKe());
            } else if (item.getItemId() == R.id.action_qlVeChungToi) {
                replaceFragment(new frgVeChungToi());
            } else if (item.getItemId() == R.id.action_LichSu) {
                replaceFragment(new frgLichSuDonHang());
            } else if (item.getItemId() == R.id.action_qlDangXuat) {
                Intent intent = new Intent(MainActivity.this, man_hinh_dang_nhap.class);
                startActivity(intent);
            }
            getSupportActionBar().setTitle(item.getTitle());
            binding.drawerLayoutMain.closeDrawer(GravityCompat.START);

            return false;
        });

        SharedPreferences sharedPreferences = getSharedPreferences("NGUOIDUNG", MODE_PRIVATE);
        String loaiTaiKhoan = sharedPreferences.getString("loaitaikhoan", "");
        int sotien = sharedPreferences.getInt("sotien", 0);
        Menu menu = navigationView.getMenu();
        if (!loaiTaiKhoan.equals("admin")) {
            menu.findItem(R.id.action_qlnguoidung).setVisible(false);
            menu.findItem(R.id.action_qlsanpham).setVisible(false);
            menu.findItem(R.id.action_qlLoaisanpham).setVisible(false);
            menu.findItem(R.id.action_qlDonHang).setVisible(false);
            menu.findItem(R.id.action_qlNapTien).setVisible(false);
            menu.findItem(R.id.action_qlThongKe).setVisible(false);
        } else {
            menu.findItem(R.id.action_LichSu).setVisible(false);
        }
        binding.txtSoTien.setText(String.valueOf(sotien));
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            binding.drawerLayoutMain.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }

    private void handleBottomNavigationItemSelected() {
        binding.navBottomMain.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.nav_bot_home) {
                replaceFragment(new frgTrangChu());
            } else if (item.getItemId() == R.id.nav_bot_sanpham) {
                replaceFragment(new frgGianHang());
            } else if (item.getItemId() == R.id.nav_bot_naptien) {
                replaceFragment(new frgNapTien());
            } else if (item.getItemId() == R.id.nav_bot_profile) {
                startActivity(new Intent(MainActivity.this, Profile.class));
            }
            getSupportActionBar().setTitle(item.getTitle());
            return true;
        });
    }

    private void replaceFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayoutMain, fragment).commit();
    }
}