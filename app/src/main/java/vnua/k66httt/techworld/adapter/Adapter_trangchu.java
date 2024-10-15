package vnua.k66httt.techworld.adapter;

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
import vnua.k66httt.techworld.databinding.ItemTrangChuBinding;

public class Adapter_trangchu extends RecyclerView.Adapter<Adapter_trangchu.ViewHolder> {
    private ArrayList<SanPham> list;
    private Context context;
    SanPhamDao dao;

    public Adapter_trangchu(ArrayList<SanPham> list, Context context) {
        this.list = list;
        this.context = context;

        dao = new SanPhamDao(context);
    }

    private OnAddToCart mAddToCartClickListener;

    private OnItemClick mListener;
    public void setOnItemClick(OnItemClick listener){
        mListener = listener;
    }
    public SanPham getViTriSp(int position) {
        if (position >= 0 && position < list.size()) {
            return list.get(position);
        }
        return null;
    }
    public void setOnAddToCartClickListenerTrangChu(OnAddToCart listener) {
        mAddToCartClickListener = listener;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemTrangChuBinding biding = ItemTrangChuBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(biding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.biding.txttenSanPham.setText(list.get(position).getTensanpham());
        holder.biding.txtgiasp.setText(String.valueOf(list.get(position).getGia()));
        Picasso.get().load(list.get(position).getAnhSanPham()).into(holder.biding.imgAnhSpTrangChu);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    mListener.onItemClick(holder.getAdapterPosition());

                }
            }
        });
        if (list.get(position).getSoluong() == 0) {
            holder.biding.btnmuahang.setVisibility(View.GONE);
        } else {
            holder.biding.btnmuahang.setVisibility(View.VISIBLE);
        }
        holder.biding.btnmuahang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mAddToCartClickListener != null) {
                    mAddToCartClickListener.onAddToCartClick(list.get(holder.getAdapterPosition()));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemTrangChuBinding biding;

        public ViewHolder(@NonNull ItemTrangChuBinding biding) {
            super(biding.getRoot());
            this.biding = biding;
        }
    }
}
