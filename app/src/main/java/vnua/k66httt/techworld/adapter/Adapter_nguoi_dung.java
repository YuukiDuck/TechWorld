package vnua.k66httt.techworld.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import vnua.k66httt.techworld.Dao.UserDao;
import vnua.k66httt.techworld.Model.User;
import vnua.k66httt.techworld.R;
import vnua.k66httt.techworld.databinding.DialogXoaNguoiDungBinding;
import vnua.k66httt.techworld.databinding.ItemQlNdBinding;

public class Adapter_nguoi_dung extends RecyclerView.Adapter<Adapter_nguoi_dung.ViewHolder> {
    private ArrayList<User> list;
    private Context context;
    private UserDao dao;

    public Adapter_nguoi_dung(ArrayList<User> list, Context context) {
        this.list = list;
        this.context = context;
        dao = new UserDao(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemQlNdBinding binding = ItemQlNdBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binding.txtmaNguoiDung.setText("Mã: " + String.valueOf(list.get(position).getMaTaiKhoan()));
        holder.binding.txtTenNguoiDung.setText("Tên: " + String.valueOf(list.get(position).getHoTen()));
        holder.binding.txtSoDienThoai.setText("Số ĐT: " + list.get(position).getSoDienThoai());
        holder.binding.txtEmail.setText("Email: " + list.get(position).getEmail());
        holder.binding.txtDiaChi.setText("Địa chỉ: " + list.get(position).getDiaChi());
        holder.binding.txtCoins.setText("Coins: " + String.valueOf(list.get(position).getSoTien()));
        User user = list.get(position);
        holder.binding.btnxoaND.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                LayoutInflater inflater = ((Activity) context).getLayoutInflater();

                DialogXoaNguoiDungBinding dialogXoaNguoiDungBinding = DialogXoaNguoiDungBinding.inflate(inflater);
                builder.setView(dialogXoaNguoiDungBinding.getRoot());

                Dialog dialog = builder.create();
                dialog.getWindow().setBackgroundDrawableResource(R.drawable.nen_dialog_doan);
                dialog.show();
                dialogXoaNguoiDungBinding.btnConfilmXoaNguoiDung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int check = dao.DeleteUser(list.get(holder.getAdapterPosition()).getMaTaiKhoan());
                        switch (check) {
                            case 1:
                                list.clear();
                                list.addAll(dao.getAllUsers());
                                notifyDataSetChanged();
                                Toast.makeText(context, "Xóa thành công người dùng", Toast.LENGTH_SHORT).show();
                                break;
                            case 0:
                                Toast.makeText(context, "Xóa không thành công người dùng", Toast.LENGTH_SHORT).show();
                                break;
                            case -1:
                                Toast.makeText(context, "Không xóa được người dùng này vì đang còn tồn tại trong hóa đơn", Toast.LENGTH_SHORT).show();
                                break;
                            default:
                                break;
                        }
                        dialog.dismiss();//close dialog
                    }
                });
                dialogXoaNguoiDungBinding.btnOutXoaNguoiDung.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemQlNdBinding binding;

        public ViewHolder(ItemQlNdBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
