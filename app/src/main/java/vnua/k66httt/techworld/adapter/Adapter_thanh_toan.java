package vnua.k66httt.techworld.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import vnua.k66httt.techworld.Dao.DanhGiaDao;
import vnua.k66httt.techworld.Dao.DonHangChiTietDao;
import vnua.k66httt.techworld.Model.DonHangChiTiet;
import vnua.k66httt.techworld.databinding.ItemConfilmThanhToanBinding;

public class Adapter_thanh_toan extends RecyclerView.Adapter<Adapter_thanh_toan.ViewHolder> {
    private ArrayList<DonHangChiTiet> list;
    private Context context;
    private DonHangChiTietDao dao;
    private DanhGiaDao dao2;

    public Adapter_thanh_toan(ArrayList<DonHangChiTiet> list, Context context) {
        this.list = list;
        this.context = context;
        dao = new DonHangChiTietDao(context);
        dao2 = new DanhGiaDao(context);
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ItemConfilmThanhToanBinding binding = ItemConfilmThanhToanBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binding.txtTenSanPham.setText("Tên sản phẩm: " + list.get(position).getTenSanPham());
        holder.binding.txtMaSanPham.setText("Mã sản phẩm: " + String.valueOf(list.get(position).getMaSanPham()));
        holder.binding.txtMaDonHang.setText("Mã đơn hàng: " + String.valueOf(list.get(position).getMaDonHang()));
        holder.binding.txtSoLuong.setText("Số lượng: " + String.valueOf(list.get(position).getSoLuong()));
        holder.binding.txtDonGia.setText("Giá: " + String.valueOf(list.get(position).getDonGia()));
        holder.binding.txtThanhTien.setText("Thành tiền: " + String.valueOf(list.get(position).getThanhTien()));
        Picasso.get().load(list.get(position).getAnhsanpham()).into(holder.binding.imgAnhSp);
        DonHangChiTiet ct=list.get(position);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemConfilmThanhToanBinding binding;

        public ViewHolder(ItemConfilmThanhToanBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}