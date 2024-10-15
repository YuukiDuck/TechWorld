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
import vnua.k66httt.techworld.Interface.OnItemClick;
import vnua.k66httt.techworld.Model.SanPham;
import vnua.k66httt.techworld.databinding.ItemListSpTrangChuBinding;

public class Adapter_sp_namngang extends RecyclerView.Adapter<Adapter_sp_namngang.ViewHolder> {
    private ArrayList<SanPham> list;
    private Context context;
    SanPhamDao spd;

    public Adapter_sp_namngang(ArrayList<SanPham> list, Context context) {
        this.list = list;
        this.context = context;
        spd = new SanPhamDao(context);
    }

    private OnItemClick mListener;

    public void setOnItemClick(OnItemClick listener) {
        mListener = listener;
    }

    public SanPham getViTriSp(int position) {
        if (position >= 0 && position < list.size()) {
            return list.get(position);
        }
        return null;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemListSpTrangChuBinding binding = ItemListSpTrangChuBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Picasso.get().load(list.get(position).getAnhSanPham()).into(holder.binding.imgAnhListSp);
        holder.binding.txtgia.setText(String.valueOf(list.get(position).getGia()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    mListener.onItemClick(holder.getAdapterPosition());

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemListSpTrangChuBinding binding;

        public ViewHolder(@NonNull ItemListSpTrangChuBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}

