package vnua.k66httt.techworld;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import vnua.k66httt.techworld.Dao.UserDao;
import vnua.k66httt.techworld.Model.User;
import vnua.k66httt.techworld.databinding.ActivityEditUserBinding;

public class EditUser extends AppCompatActivity {
    ActivityEditUserBinding binding;
    UserDao dao;
    User user;
    private ArrayList<User> list = new ArrayList<>();
    String email, matkhaucu, matkhaumoi, nhaplaimatkhaumoi, tenDangNhap, hovaten, matkhau, diachi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
