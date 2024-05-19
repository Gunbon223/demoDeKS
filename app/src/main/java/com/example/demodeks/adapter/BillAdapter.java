package com.example.demodeks.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demodeks.R;
import com.example.demodeks.entities.HotelBill;

import java.util.ArrayList;
import java.util.List;

public class BillAdapter extends RecyclerView.Adapter<BillAdapter.ViewHolder> {
    Context context;
    List<HotelBill> BillList;

    public BillAdapter(Context context, List<HotelBill> billList) {
        this.context = context;
        BillList = billList;
    }

    @NonNull
    @Override
    public BillAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_items, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BillAdapter.ViewHolder holder, int position) {
        HotelBill hotelBill = BillList.get(position);
        double total = hotelBill.total();
        String totalRounded = String.format("%.3f", total);
        String roomPriceRounded = String.format("%.1f", total);

        holder.txtView01.setText("Họ tên: "+hotelBill.getHoTen());
        holder.txtView02.setText("Số phòng: " +hotelBill.getRoomNumber());
        holder.txtView03.setText(totalRounded);
        holder.itemView.setOnClickListener(v -> {
            Toast.makeText(context, "Họ tên: HH" +  "Số hoá đơn:" + SoTienLonHon(total,BillList) , Toast.LENGTH_SHORT).show();
        });
    }

    public int SoTienLonHon(double soTien, List<HotelBill> db) {
        int count =0;
        for (HotelBill hotelBill : db) {
            if (soTien < hotelBill.total()) {
                count++;
            }
        }
        return count;
    }

    @Override
    public int getItemCount() {
        if (BillList != null) {
            return BillList.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener{
        TextView txtView01 ;
        TextView txtView02 ;
        TextView txtView03 ;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtView01 = itemView.findViewById(R.id.nameTxt);
            txtView02 = itemView.findViewById(R.id.roomTxt);
            txtView03 = itemView.findViewById(R.id.SoTien);
            itemView.setOnLongClickListener(this);

        }

        @Override
        public boolean onLongClick(View v) {
            int postion = getAdapterPosition();
            HotelBill bill =  BillList.get(postion);
            double total = bill.total();
            Toast.makeText(context, "Họ tên: HH" +  "Số hoá đơn lon hon: " + SoTienLonHon(total,BillList) , Toast.LENGTH_SHORT).show();
            return true;
        }
    }
}
