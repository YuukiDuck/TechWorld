package vnua.k66httt.techworld.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import vnua.k66httt.techworld.Dao.UserDao;
import vnua.k66httt.techworld.Model.User;
import vnua.k66httt.techworld.R;
import vnua.k66httt.techworld.adapter.Adapter_nguoi_dung;
import vnua.k66httt.techworld.databinding.FragmentFrgQuanLyNguoiDungBinding;

public class frgQuanLyNguoiDung extends Fragment {
    private FragmentFrgQuanLyNguoiDungBinding binding;
    private View vView;
    private UserDao dao;
    private Adapter_nguoi_dung adapterNguoiDung;
    private ArrayList<User> list = new ArrayList<>();

    public frgQuanLyNguoiDung() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFrgQuanLyNguoiDungBinding.inflate(inflater, container, false);
        vView = binding.getRoot();
        dao = new UserDao(getContext());
        list = dao.getAllUsers();
        RecyclerView rcv = binding.rcvNguoiDung;
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rcv.setLayoutManager(layoutManager);
        adapterNguoiDung = new Adapter_nguoi_dung(list, getContext());
        rcv.setAdapter(adapterNguoiDung);
        // Inflate the layout for this fragment
        binding.flNguoiDung.setOnClickListener(view -> {
            frgAddNguoiDung frgAddNguoiDung = new frgAddNguoiDung();
            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
            transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right);
            transaction.replace(R.id.frameLayoutMain, frgAddNguoiDung);
            transaction.addToBackStack(null);
            transaction.commit();
        });

        Bundle bundle = getArguments();
        if (bundle != null) {
            String matKhau = bundle.getString("matKhau");
            String hoTen = bundle.getString("hoTen");
            String email = bundle.getString("email");
            String soDienThoai = bundle.getString("soDienThoai");
            String diaChi = bundle.getString("diaChi");
            int tien = bundle.getInt("soTien");
            String loaiTaiKhoan = bundle.getString("loaiTaiKhoan");

            User nd = new User();
            nd.setMatKhau(matKhau);
            nd.setHoTen(hoTen);
            nd.setEmail(email);
            nd.setSoDienThoai(soDienThoai);
            nd.setDiaChi(diaChi);
            nd.setSoTien(tien);
            nd.setLoaiTaiKhoan(loaiTaiKhoan);
            boolean kiemTra = dao.checkDangKy(nd);
            if (kiemTra) {
                list.clear();
                list.addAll(dao.getAllUsers());
                adapterNguoiDung.notifyDataSetChanged();
                Toast.makeText(getContext(), "Thêm người dùng thành công", Toast.LENGTH_SHORT).show();
            }
        }

        return vView;
    }
}