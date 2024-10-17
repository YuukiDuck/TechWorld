package vnua.k66httt.techworld.fragment;

import static android.content.Context.MODE_PRIVATE;

import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import vnua.k66httt.techworld.Dao.DonHangChiTietDao;
import vnua.k66httt.techworld.Dao.DonHangDao;
import vnua.k66httt.techworld.Dao.GioHangDao;
import vnua.k66httt.techworld.Dao.SanPhamDao;
import vnua.k66httt.techworld.Model.DonHang;
import vnua.k66httt.techworld.Model.GioHang;
import vnua.k66httt.techworld.Model.SanPham;
import vnua.k66httt.techworld.adapter.Adapter_don_hang;
import vnua.k66httt.techworld.adapter.Adapter_gio_hang;
import vnua.k66httt.techworld.databinding.FragmentFrgGioHangBinding;

public class frgGioHang extends Fragment implements Adapter_gio_hang.TotalPriceListener {

    private ArrayList<GioHang> list = new ArrayList<>();
    private Adapter_gio_hang gioHangAdapter;
    FragmentFrgGioHangBinding binding;
    View gView;

    GioHangDao gioHangDao;

    private DonHangDao donHangDao;
    private Adapter_don_hang adapterDonHang;
    private frgQuanLyDonHang frgQuanLyDonHang;
    private ArrayList<DonHang> listDonHang = new ArrayList<>();
    private DonHangChiTietDao chiTietDao;
    private ArrayList<SanPham> sanPhams = new ArrayList<>();
    private SanPhamDao sanPhamDao;

    public frgGioHang() {
    }

    private void displayCart(ArrayList<GioHang> cartList) {
        RecyclerView rcv = binding.rcvGioHang;
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rcv.setLayoutManager(layoutManager);
        //
        if (gioHangAdapter == null) {
            gioHangAdapter = new Adapter_gio_hang(getContext(), cartList);
            rcv.setAdapter(gioHangAdapter);
        } else {
            gioHangAdapter.updateCartList(cartList);
            gioHangAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onTotalPriceUpdated(int totalAmount) {

    }
}
