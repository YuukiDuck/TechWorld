package vnua.k66httt.techworld.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;

import vnua.k66httt.techworld.Dao.NguoiDungDao;
import vnua.k66httt.techworld.Model.NguoiDung;
import vnua.k66httt.techworld.adapter.Adapter_quanly_nap_tien;
import vnua.k66httt.techworld.databinding.FragmentFrgQuanLyNapTienBinding;

public class frgQuanLyNapTien extends Fragment {
    View view;
    FragmentFrgQuanLyNapTienBinding binding;
    ArrayList<NguoiDung> list;
    Adapter_quanly_nap_tien adapter;
    NguoiDungDao dao;

    public frgQuanLyNapTien() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentFrgQuanLyNapTienBinding.inflate(inflater, container, false);
        // Inflate the layout for this fragment
        view = binding.getRoot();
        dao = new NguoiDungDao(getContext());
        list = dao.getAllUsers();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        binding.rcvNapTien.setLayoutManager(layoutManager);
        adapter = new Adapter_quanly_nap_tien(list, getContext());
        binding.rcvNapTien.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        return view;
    }
}
