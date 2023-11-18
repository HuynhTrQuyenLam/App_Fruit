package com.example.pjvthl.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pjvthl.DBHelper;
import com.example.pjvthl.MainActivity;
import com.example.pjvthl.R;
import com.example.pjvthl.Utils;
import com.example.pjvthl.adapter.CartAdapter;
import com.example.pjvthl.adapter.ProDetailAdapter;
import com.example.pjvthl.model.ProductDB;

public class ProDetailFragment extends Fragment {
    RecyclerView rcvProDetail;
    DBHelper dbHelper;

    ProductDB productDB;
    ImageView img;
    TextView tensp, gia, mota;

    private Button btnThanhToan;
    private View mView;
    private MainActivity mainActivity;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product_detail, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            productDB = (ProductDB) bundle.getSerializable("spdetail");
            // Sử dụng đối tượng Serializable ở đây
        }
        img = view.findViewById(R.id.hinhsanpham);
        tensp = view.findViewById(R.id.tensanpham);
        gia = view.findViewById(R.id.textviewgia);
        mota = view.findViewById(R.id.mota);
        img.setImageBitmap(Utils.converttoBitmapFromAsset(getContext(),productDB.getImages()));
        tensp.setText(productDB.getNamepro());
        gia.setText(productDB.getPrice().toString());
        mota.setText(productDB.getDecs());

    }
}
