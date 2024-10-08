package vnua.k66httt.techworld.fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.Calendar;

import vnua.k66httt.techworld.Dao.ThongKeDao;
import vnua.k66httt.techworld.Model.DonHangChiTiet;
import vnua.k66httt.techworld.adapter.Adapter_top3_sanphambanchay;
import vnua.k66httt.techworld.databinding.FragmentThongKeBinding;

public class frgThongKe extends Fragment {
    FragmentThongKeBinding binding;
    View view;

    public frgThongKe() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInsatanceState) {
        binding = FragmentThongKeBinding.inflate(inflater, container, false);
        ThongKeDao dao = new ThongKeDao(getContext());
        ArrayList<DonHangChiTiet>list = dao.getTop3();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        binding.rvctop3sp.setLayoutManager(layoutManager);
        Adapter_top3_sanphambanchay adapter = new Adapter_top3_sanphambanchay(list, getContext());
        binding.rvctop3sp.setAdapter(adapter);
        Calendar calendar = Calendar.getInstance();

        binding.btnlichBatDau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                                Calendar selectedCalendar = Calendar.getInstance();
                                selectedCalendar.set(year, month, dayOfMonth);

                                // Kiểm tra nếu ngày chọn là ngày hiện tại hoặc sau ngày hiện tại và không quá ngày hiện tại
                                if (!selectedCalendar.after(Calendar.getInstance())) {
                                    String ngay = (dayOfMonth < 10) ? "0" + dayOfMonth : String.valueOf(dayOfMonth);
                                    String thang = ((month + 1) < 10) ? "0" + (month + 1) : String.valueOf(month + 1);
                                    binding.btnlichBatDau.setText(year + "/" + thang + "/" + ngay);
//                                    binding.btnlichBatDau.setText(ngay+"/"+thang+"/"+year);
                                } else {
                                    // Hiển thị thông báo hoặc thực hiện hành động khác nếu người dùng chọn ngày trước hoặc bằng ngày hiện tại.
                                    // Ví dụ: Toast.makeText(getContext(), "Chọn ngày trong khoảng từ ngày hiện tại đến quá khứ", Toast.LENGTH_SHORT).show();
                                }
                            }
                        },
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)
                );

                // Đặt giới hạn cho DatePickerDialog để chỉ cho phép chọn ngày trong khoảng từ ngày hiện tại đến quá khứ
                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });

        binding.btnlichKetThuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                                Calendar selectedCalendar = Calendar.getInstance();
                                selectedCalendar.set(year, month, dayOfMonth);

                                if (!selectedCalendar.after(Calendar.getInstance())) {
                                    String ngay = (dayOfMonth < 10) ? "0" + dayOfMonth : String.valueOf(dayOfMonth);
                                    String thang = ((month + 1) < 10) ? "0" + (month + 1) : String.valueOf(month + 1);

                                    binding.btnlichKetThuc.setText(year + "/" + thang + "/" + ngay);
//                                    binding.btnlichBatDau.setText(ngay+"/"+thang+"/"+year);
                                } else {
                                    // Hiển thị thông báo hoặc thực hiện hành động khác nếu người dùng chọn ngày trước hoặc bằng ngày hiện tại.
                                    // Ví dụ: Toast.makeText(getContext(), "Chọn ngày trong khoảng từ ngày hiện tại đến quá khứ", Toast.LENGTH_SHORT).show();
                                }
                            }
                        },
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)
                );

                // Đặt giới hạn cho DatePickerDialog để chỉ cho phép chọn ngày trong khoảng từ ngày hiện tại đến quá khứ
                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });


        return view;
    }
}
