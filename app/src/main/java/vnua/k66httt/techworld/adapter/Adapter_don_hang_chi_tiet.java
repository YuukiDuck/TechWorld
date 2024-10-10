package vnua.k66httt.techworld.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import vnua.k66httt.techworld.Dao.DanhGiaDao;
import vnua.k66httt.techworld.Dao.DonHangChiTietDao;
import vnua.k66httt.techworld.Model.DonHangChiTiet;
import vnua.k66httt.techworld.databinding.ItemDonHangChiTietBinding;

public class Adapter_don_hang_chi_tiet extends RecyclerView.Adapter<Adapter_don_hang_chi_tiet.ViewHolder> {

    private ArrayList<DonHangChiTiet> list;
    private Context context;
    private DonHangChiTietDao dao;
    private DanhGiaDao dao2;

    public Adapter_don_hang_chi_tiet(ArrayList<DonHangChiTiet> list, Context context) {
        this.list = list;
        this.context = context;
        dao = new DonHangChiTietDao(context);
        dao2 = new DanhGiaDao(context);
    }

    @NonNull
    @Override
    public Adapter_don_hang_chi_tiet.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemDonHangChiTietBinding binding = ItemDonHangChiTietBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_don_hang_chi_tiet.ViewHolder holder, int position) {
        holder.binding.txtDonGia.setText("Giá:" + String.valueOf(list.get(position).getDonGia()));
        holder.binding.txtSoLuong.setText("Mã chi tiết đơn:" + String.valueOf(list.get(position).getDonGia()));
        holder.binding.txtMaDonHang.setText("Mã đơn hàng:" + String.valueOf(list.get(position).getDonGia()));
        holder.binding.txtMaSanPham.setText("Gia:" + String.valueOf(list.get(position).getDonGia()));
        holder.binding.txttensanpham.setText("Tên sản phẩm:" + String.valueOf(list.get(position).getDonGia()));
        holder.binding.txtmaChiTietDon.setText("Số lượng:" + String.valueOf(list.get(position).getDonGia()));
        holder.binding.txtThanhTien.setText("Thành tiền:" + String.valueOf(list.get(position).getDonGia()));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemDonHangChiTietBinding binding;

        public ViewHolder(ItemDonHangChiTietBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
