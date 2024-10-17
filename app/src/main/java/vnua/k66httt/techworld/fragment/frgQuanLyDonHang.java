package vnua.k66httt.techworld.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;

import vnua.k66httt.techworld.Dao.DonHangChiTietDao;
import vnua.k66httt.techworld.Dao.DonHangDao;
import vnua.k66httt.techworld.Interface.OnItemClick;
import vnua.k66httt.techworld.Model.DonHang;
import vnua.k66httt.techworld.R;
import vnua.k66httt.techworld.adapter.Adapter_don_hang;
import vnua.k66httt.techworld.databinding.FragmentFrgQuanLyDonHangBinding;

public class frgQuanLyDonHang extends Fragment {
    public frgQuanLyDonHang() {
        // Required empty public constructor
    }

    FragmentFrgQuanLyDonHangBinding binding;
    private ArrayList<DonHang> list = new ArrayList<>();
    private DonHangDao dao;
    private Adapter_don_hang adapterDonHang;
    DonHangChiTietDao chiTietDao;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFrgQuanLyDonHangBinding.inflate(inflater, container, false);
        dao = new DonHangDao(getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        binding.rcvDonHang.setLayoutManager(layoutManager);
        list = dao.getDsDonHang();
        adapterDonHang = new Adapter_don_hang(list, getContext());
        binding.rcvDonHang.setAdapter(adapterDonHang);
        chiTietDao = new DonHangChiTietDao(getContext());
        adapterDonHang.setOnItemClick(new OnItemClick() {
            @Override
            public void onItemClick(int position) {
                DonHang donHang = list.get(position);
                int maDonHang = donHang.getMaDonHang();

                Bundle bundle = new Bundle();
                bundle.putInt("maDonHang", maDonHang);
                frgDonHangChiTiet frgDonHangChiTiet = new frgDonHangChiTiet();
                frgDonHangChiTiet.setArguments(bundle);
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right);
                fragmentTransaction.replace(R.id.frameLayoutMain, frgDonHangChiTiet);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        return binding.getRoot();
    }
}
