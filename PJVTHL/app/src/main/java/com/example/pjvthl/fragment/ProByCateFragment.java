package com.example.pjvthl.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pjvthl.DBHelper;
import com.example.pjvthl.R;
import com.example.pjvthl.adapter.ProbyCateAdapter;


public class ProByCateFragment extends Fragment {

    RecyclerView rcvprobycate;
    public String cate;

    DBHelper dbHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pro_by_cate, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        cate = bundle.getString("namecate");
        rcvprobycate = view.findViewById(R.id.rcvprobycate);
        dbHelper = new DBHelper(view.getContext());
        ProbyCateAdapter probyCateAdapter = new ProbyCateAdapter(dbHelper,view.getContext(),cate);
        rcvprobycate.setAdapter(probyCateAdapter);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(view.getContext(),2);
        rcvprobycate.setLayoutManager(gridLayoutManager);
    }
}