package vnua.k66httt.techworld.adapter;

import android.content.ClipData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import vnua.k66httt.techworld.Dao.SanPhamDao;
import vnua.k66httt.techworld.Interface.OnAddToCart;
import vnua.k66httt.techworld.Interface.OnItemClick;
import vnua.k66httt.techworld.Model.SanPham;
import vnua.k66httt.techworld.databinding.ItemGianHangBinding;

public class Adapter_gian_hang extends RecyclerView.Adapter<Adapter_gian_hang.ViewHolder> {

    private Context context;
    private  ArrayList<SanPham> list;
    SanPhamDao dao;
    private ArrayList<Boolean> isClickThemVaoGio;


    public Adapter_gian_hang(Context context, ArrayList<SanPham> list) {
        this.context = context;
        this.list = list;

        dao = new SanPhamDao(context);

    }

    public void clearData(){
        list.clear();
        notifyDataSetChanged();
    }

    public void setData(ArrayList<SanPham> newList){
        list.clear();
        list.addAll(newList);
        notifyDataSetChanged();
    }

    public void addAll(ArrayList<SanPham> data){
        list.addAll(data);
        notifyDataSetChanged();
    }

    //Biến để lưu trữ listener
    private OnItemClick mListener;

    // Phương thức thiết lập
    public void setOnItemCLickListener(OnItemClick listener){
        mListener = listener;
    }

    private OnAddToCart mAddToCartClickListener;

    public void setOnAddToCartClickListener (OnAddToCart listener){
        mAddToCartClickListener = listener;
    }

    public SanPham getViTriSanPham(int position) {
        if (position >= 0 && position < list.size()) {
            return list.get(position);
        }
        return null;
    }

    @NonNull
    @Override
    public Adapter_gian_hang.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemGianHangBinding binding = ItemGianHangBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_gian_hang.ViewHolder holder, int position) {
        SanPham sanPham =list.get(position);
        //thiết lập dữ liệu
        holder.binding.txttenSp.setText("Tên sp:" + list.get(position).getTensanpham());
        holder.binding.txtgiaSp.setText("Giá sp:" + list.get(position).getTensanpham());
        holder.binding.txttrangThaiSanPham.setText("Tồn kho:" + list.get(position).getTensanpham());
        holder.binding.txtSoluongdaban.setText("Số lượng đã bán:" + list.get(position).getTensanpham());
        Picasso.get().load(list.get(position).getAnhSanPham()).into(holder.binding.imgAnhspGianHang);

        //Kiểm tra trạng thái và nút thêm giỏ hàng
        if (list.get(position).getSoluong() == 0){
            holder.binding.btnThemvaogio.setVisibility(ViewGroup.GONE);
            holder.binding.txtHetHang.setVisibility(View.VISIBLE);
        } else {
            holder.binding.btnThemvaogio.setVisibility(View.VISIBLE);
            holder.binding.txtHetHang.setVisibility(View.GONE);
            holder.binding.btnThemvaogio.setOnClickListener(View -> {
                if (mAddToCartClickListener != null) {
                    mAddToCartClickListener.onAddToCartClick(list.get(holder.getAdapterPosition()));
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ItemGianHangBinding binding;

        public ViewHolder(ItemGianHangBinding binding){
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
