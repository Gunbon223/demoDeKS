package com.example.demodeks;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.example.demodeks.adapter.BillAdapter;
import com.example.demodeks.database.BillDB;
import com.example.demodeks.entities.HotelBill;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<HotelBill> listBill;
    BillDB db;
    EditText search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyleView);
        search = findViewById(R.id.search);
        listBill = new ArrayList<>();
        db= new BillDB(this);
//        HotelBill demo1 = new HotelBill(120, 2000, 2, "Peter");
//        HotelBill demo2 = new HotelBill(1090, 120000, 4, "Alzander");
//        db.addBill(demo1);
//        db.addBill(demo2);

        db.getAllBill(listBill);
        listBill = sortBill(listBill);

        BillAdapter adapter = new BillAdapter(this, listBill);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        search.addTextChangedListener(new TextWatcher() {
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        // No action needed here
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        try {
            double inputAmount = Double.parseDouble(s.toString());
            List<HotelBill> filteredList = new ArrayList<>();
            for (HotelBill bill : listBill) {
                if (bill.total() > inputAmount) {
                    filteredList.add(bill);
                }
            }
            BillAdapter adapter = new BillAdapter(MainActivity.this, filteredList);
            recyclerView.setAdapter(adapter);
        } catch (NumberFormatException e) {
            // Input is not a valid number, show all bills
            BillAdapter adapter = new BillAdapter(MainActivity.this, listBill);
            recyclerView.setAdapter(adapter);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {
        // No action needed here
    }
    });

    }



    public List<HotelBill> sortBill(List<HotelBill> listBill) {
        listBill.sort((o1, o2) -> {
            if (o1.getRoomNumber() > o2.getRoomNumber()) {
                return 1;
            } else if (o1.getRoomNumber() < o2.getRoomNumber()) {
                return -1;
            } else {
                return 0;
            }
        });
        return listBill;
    }
}