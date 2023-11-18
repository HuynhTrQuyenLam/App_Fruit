package com.example.pjvthl.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.pjvthl.MainActivity;
import com.example.pjvthl.R;

public class DatHangFragment extends Fragment {

    private Button btnChonPTTT,btnDatHang;
    private View mView;
    private MainActivity mainActivity;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_dat_hang, container, false);
        mainActivity = (MainActivity) getActivity();

        btnChonPTTT = mView.findViewById(R.id.btn_ChonPTTT);
        btnChonPTTT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment secondFrag = new PhuongThucThanhToanFragment();
                FragmentTransaction fm = getActivity().getSupportFragmentManager().beginTransaction();
                fm.replace(R.id.content_frame,secondFrag).commit();
            }
        });

        btnDatHang=mView.findViewById(R.id.btn_DatHang);
        btnDatHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment secondFrag = new DatHangThanhCongFragment();
                FragmentTransaction fm = getActivity().getSupportFragmentManager().beginTransaction();
                fm.replace(R.id.content_frame,secondFrag).commit();
            }
        });

        return mView;    }
}