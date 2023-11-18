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


public class PhuongThucThanhToanFragment extends Fragment {

    private Button btnXacNhan;
    private View mView;
    private MainActivity mainActivity;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_phuong_thuc_thanh_toan, container, false);
        mainActivity = (MainActivity) getActivity();

        btnXacNhan = mView.findViewById(R.id.btnXacNhan);
        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment secondFrag = new DatHangFragment();
                FragmentTransaction fm = getActivity().getSupportFragmentManager().beginTransaction();
                fm.replace(R.id.content_frame,secondFrag).commit();
            }
        });
        return mView;  }
}