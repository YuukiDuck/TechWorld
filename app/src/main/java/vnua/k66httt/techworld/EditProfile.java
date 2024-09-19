package vnua.k66httt.techworld;

import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import vnua.k66httt.techworld.Dao.UserDao;
import vnua.k66httt.techworld.Model.User;

public class EditProfile extends AppCompatActivity {
    ActivityEditProfileBindling biding;
    UserDao dao;
    User user;
    private ArrayList<User> list = new ArrayList<>();
    String Email, matkhaucu, matkhaumoi, nhaplaimatkhaumoi, tendangnhap, sodienthoai, matkhau;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}
