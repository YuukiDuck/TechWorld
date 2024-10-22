package vnua.k66httt.techworld.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;

import vnua.k66httt.techworld.Dao.DonHangChiTietDao;
import vnua.k66httt.techworld.Model.DonHangChiTiet;
import vnua.k66httt.techworld.R;
import vnua.k66httt.techworld.adapter.adapter_don_hang_chi_tiet;
import vnua.k66httt.techworld.databinding.FragmentFrgDonHangChiTietBinding;

public class frgDonHangChiTiet extends Fragment {
    FragmentFrgDonHangChiTietBinding binding;
    public frgDonHangChiTiet() {
        // Required empty public constructor
    }

    private ArrayList<DonHangChiTiet> list = new ArrayList<>();

    private adapter_don_hang_chi_tiet adapterDonHangChiTiet;
    DonHangChiTietDao chiTietDao;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFrgDonHangChiTietBinding.inflate(inflater, container, false);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        binding.rcvDonHangChiTiet.setLayoutManager(layoutManager);
        chiTietDao = new DonHangChiTietDao(getContext());
        Bundle bundle = getArguments();
        if (bundle != null) {
            int maDonHang = bundle.getInt("maDonHang", 0);
            Log.d("Mã đơn hàng", String.valueOf(maDonHang));
            if (maDonHang != 0) {
                list = chiTietDao.getChiTietDonHangByMaDonHang(maDonHang);
                adapterDonHangChiTiet = new adapter_don_hang_chi_tiet(list, getContext());
                binding.rcvDonHangChiTiet.setAdapter(adapterDonHangChiTiet);

            }
        }

        binding.btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                frgQuanLyDonHang frgQuanLyDonHang=new frgQuanLyDonHang();//fragment được chuyển đến sau khi ấn
                FragmentManager fragmentManager=getParentFragmentManager();
                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                fragmentTransaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right);
                fragmentTransaction.replace(R.id.frameLayoutMain,frgQuanLyDonHang);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        return binding.getRoot();
    }
}
