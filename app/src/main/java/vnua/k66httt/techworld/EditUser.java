package vnua.k66httt.techworld;

import android.os.Bundle;

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
    String Email, matkhaucu, matkhaumoi, nhaplaimatkhaumoi, tendangnhap, sodienthoai, matkhau;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}
