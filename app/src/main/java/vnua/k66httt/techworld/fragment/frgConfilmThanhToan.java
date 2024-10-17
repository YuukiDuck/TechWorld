package vnua.k66httt.techworld.fragment;

import static vnua.k66httt.techworld.databinding.FragmentFrgConfilmThanhToanBinding.*;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;

import vnua.k66httt.techworld.Dao.DonHangChiTietDao;
import vnua.k66httt.techworld.Model.DonHangChiTiet;
import vnua.k66httt.techworld.R;
import vnua.k66httt.techworld.adapter.Adapter_thanh_toan;
import vnua.k66httt.techworld.databinding.FragmentFrgConfilmThanhToanBinding;

public class frgConfilmThanhToan extends Fragment {
    public frgConfilmThanhToan(){

    }
    private FragmentFrgConfilmThanhToanBinding binding;
    private ArrayList<DonHangChiTiet> list = new ArrayList<>();
    private DonHangChiTietDao chiTietDao;
    private Adapter_thanh_toan adapterThanhToan;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentFrgConfilmThanhToanBinding.inflate(inflater, container, false);
        // Inflate the layout for this fragment
        chiTietDao = new DonHangChiTietDao(getContext());
        binding.rcv.setLayoutManager(new LinearLayoutManager(getContext()));

        Bundle bundle = getArguments();

        if (bundle != null) {
            int maDonHang = bundle.getInt("maDonHang, 0");
            if (maDonHang != 0) {
                list = chiTietDao.getChiTietDonHangByMaDonHang(maDonHang);
                adapterThanhToan = new Adapter_thanh_toan(list, getContext());
                binding.rcv.setAdapter(adapterThanhToan);
            }
        }
        binding.btntieptucmua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                frgGioHang frgGioHang=new frgGioHang();
                FragmentManager fragmentManager=getParentFragmentManager();
                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                fragmentTransaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right);
                fragmentTransaction.replace(R.id.frameLayoutMain,frgGioHang);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        return binding.getRoot();
    }
}
