package com.example.pjvthl.fragment;

import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.pjvthl.DBHelper;
import com.example.pjvthl.MainActivity;
import com.example.pjvthl.R;
import com.example.pjvthl.adapter.CartAdapter;


public class CartFragment extends Fragment {

    RecyclerView rcvCart;
    DBHelper dbHelper;

    private Button btnThanhToan;
    private ImageView btnDelete;
    private View mView;
    private MainActivity mainActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       mView = inflater.inflate(R.layout.fragment_cart, container, false);
       mainActivity = (MainActivity) getActivity();

        btnThanhToan = mView.findViewById(R.id.btn_thanhtoan);
        btnThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment secondFrag = new DatHangFragment();
                FragmentTransaction fm = getActivity().getSupportFragmentManager().beginTransaction();
                fm.replace(R.id.content_frame,secondFrag).commit();
            }
        });
        return mView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rcvCart = view.findViewById(R.id.rcvCart);
        dbHelper = new DBHelper(view.getContext());
        dbHelper.CopyDatabaseFromAssets();
        CartAdapter cartAdapter = new CartAdapter(dbHelper,getContext());
        rcvCart.setAdapter(cartAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext(),LinearLayoutManager.VERTICAL,false);
        rcvCart.setLayoutManager(linearLayoutManager);
    }
}