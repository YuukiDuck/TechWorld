package vnua.k66httt.techworld.fragment;

import static android.content.Context.MODE_PRIVATE;

import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import vnua.k66httt.techworld.Dao.DanhGiaDao;
import vnua.k66httt.techworld.Dao.DonHangChiTietDao;
import vnua.k66httt.techworld.Interface.OnAddDanhGia;
import vnua.k66httt.techworld.Model.DonHangChiTiet;
import vnua.k66httt.techworld.R;
import vnua.k66httt.techworld.adapter.Adapter_ls_don_hang_chi_tiet;
import vnua.k66httt.techworld.databinding.DialogDanhGiaBinding;
import vnua.k66httt.techworld.databinding.FragmentFrgLsDonHangChiTietBinding;

public class frg_ls_don_hang_chi_tiet extends Fragment {
    public frg_ls_don_hang_chi_tiet() {
        // Required empty public constructor
    }

    FragmentFrgLsDonHangChiTietBinding binding;
    private ArrayList<DonHangChiTiet> list = new ArrayList<>();

    private Adapter_ls_don_hang_chi_tiet adapterLsDonHangChiTiet;
    DonHangChiTietDao chiTietDao;
    DanhGiaDao danhGiaDao;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFrgLsDonHangChiTietBinding.inflate(inflater, container, false);
        // Inflate the layout for this fragment
        danhGiaDao = new DanhGiaDao(getContext());


        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        binding.rcvLichSuDonHang.setLayoutManager(layoutManager);
        chiTietDao = new DonHangChiTietDao(getContext());
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("NGUOIDUNG", MODE_PRIVATE);
        int mand = sharedPreferences.getInt("mataikhoan", 0);
        Bundle bundle = getArguments();
        if (bundle != null) {
            int maDonHang = bundle.getInt("maDonHang", 0);
            Log.d("Mã đơn hàng", String.valueOf(maDonHang));
            if (maDonHang != 0) {
                list = chiTietDao.getChiTietDonHangByMaDonHang(maDonHang);
                adapterLsDonHangChiTiet = new Adapter_ls_don_hang_chi_tiet(list, getContext());
                binding.rcvLichSuDonHang.setAdapter(adapterLsDonHangChiTiet);
                adapterLsDonHangChiTiet.setOnAddDanhGia(new OnAddDanhGia() {
                    @Override
                    public void onAddDanhia(DonHangChiTiet donHangChiTiet) {


                        LayoutInflater layoutInflater = getLayoutInflater();
                        DialogDanhGiaBinding dialogDanhGiaBinding = DialogDanhGiaBinding.inflate(layoutInflater);
                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                        builder.setView(dialogDanhGiaBinding.getRoot());
                        AlertDialog dialogD = builder.create();
                        dialogD.getWindow().setBackgroundDrawableResource(R.drawable.nen_dialog_doan);
                        dialogD.show();


                        LocalDate currentDate = LocalDate.now();
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                        String ngayHienTai = currentDate.format(formatter);


                        dialogDanhGiaBinding.btnthembinhluan.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                String danhgia = dialogDanhGiaBinding.eddanhgia.getText().toString();
                                String nhanxet = dialogDanhGiaBinding.ednhanxet.getText().toString();
                                if (danhgia.isEmpty()) {
                                    dialogDanhGiaBinding.eddanhgia.setError("Vui lòng không để trống");
                                } else {
                                    dialogDanhGiaBinding.eddanhgia.setError(null);
                                }
                                if (nhanxet.isEmpty()) {
                                    dialogDanhGiaBinding.ednhanxet.setError("Vui lòng không để trống");
                                } else {
                                    dialogDanhGiaBinding.ednhanxet.setError(null);
                                }
                                if (dialogDanhGiaBinding.eddanhgia.getError() == null && dialogDanhGiaBinding.ednhanxet.getError() == null) {
                                    danhGiaDao.addDanhGia(mand, donHangChiTiet.getMaSanPham(), danhgia, nhanxet, ngayHienTai);
                                    Toast.makeText(getContext(), "Thêm  thành công", Toast.LENGTH_SHORT).show();
                                    dialogD.dismiss();
                                }
                            }
                        });
                    }
                });
            }
        }

        binding.imgBackLichSu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                frg_lich_su_don_hang frgLichSuDonHang = new frg_lich_su_don_hang();//fragment được chuyển đến sau khi ấn
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right);
                fragmentTransaction.replace(R.id.frameLayoutMain, frgLichSuDonHang);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        return binding.getRoot();
    }
}