package vnua.k66httt.techworld.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import vnua.k66httt.techworld.Dao.LoaiSanPhamDao;
import vnua.k66httt.techworld.Model.LoaiSanPham;
import vnua.k66httt.techworld.R;

public class Adapter_loai_san_pham extends RecyclerView.Adapter<Adapter_loai_san_pham.ViewHolder> {
    private final Context context;
    private final ArrayList<LoaiSanPham> list;
    LoaiSanPhamDao dao;

    public Adapter_loai_san_pham(Context context, ArrayList<LoaiSanPham> list) {
        this.context = context;
        this.list = list;
        dao = new LoaiSanPhamDao(context);
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_loaisanpham, parent, false);
        return new  ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_loai_san_pham.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtmaloaisanpham, txttenloaisanpham;
        ImageButton btnxoa;

        public ViewHolder (@NonNull View itemView){
            super(itemView);
            txtmaloaisanpham = itemView.findViewById(R.id.txtma_loai_san_pham);
            txttenloaisanpham = itemView.findViewById(R.id.txtten_loai_san_pham);
            btnxoa = itemView.findViewById(R.id.btnxoa);
        }
    }
}
