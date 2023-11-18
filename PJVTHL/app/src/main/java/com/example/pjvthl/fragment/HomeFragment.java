package com.example.pjvthl.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pjvthl.DBHelper;
import com.example.pjvthl.MainActivity;
import com.example.pjvthl.R;
import com.example.pjvthl.adapter.ListproductAdapter;
import com.example.pjvthl.adapter.SanPhamAdapter;
import com.example.pjvthl.adapter.SanPhamMoiRaAdapter;
import com.example.pjvthl.adapter.SanPhamNoiBatAdapter;
import com.example.pjvthl.model.Listproduct;
import com.example.pjvthl.model.SanPham;
import com.example.pjvthl.model.SanPhamMoiRa;
import com.example.pjvthl.model.SanPhamNoiBat;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment implements ListproductAdapter.Listener{
    private RecyclerView rcvlistproduct,rcv_SanPhamNoiBat, rcv_SanPhamMoiRa,rcv_SanPham;
    private View mView;
    private MainActivity mainActivity;
    private ListproductAdapter listproductAdapter;
    private SanPhamMoiRaAdapter sanPhamMoiRaAdapter;
    private SanPhamAdapter sanPhamAdapter;
    private Button btnThanhToan;

    DBHelper dbHelper;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_home, container, false);
        mainActivity = (MainActivity) getActivity();
        dbHelper = new DBHelper(mView.getContext());
        dbHelper.CopyDatabaseFromAssets();

        //Danh mục sản phẩm
        rcvlistproduct = mView.findViewById(R.id.rcv_listproduct);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mainActivity,LinearLayoutManager.HORIZONTAL,false);
        rcvlistproduct.setLayoutManager(linearLayoutManager);
        listproductAdapter = new ListproductAdapter();
        listproductAdapter.setData(getProduct(),HomeFragment.this);
        rcvlistproduct.setAdapter(listproductAdapter);

        //Sản phẩm nổi bật
        rcv_SanPhamNoiBat = mView.findViewById(R.id.rcv_SanPhamNoiBat);
        SanPhamMoiRaAdapter sanPhamNoiBatAdapter= new SanPhamMoiRaAdapter(dbHelper,mView.getContext(),"outstanding", getActivity());
        rcv_SanPhamNoiBat.setAdapter(sanPhamNoiBatAdapter);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(mView.getContext(),LinearLayoutManager.HORIZONTAL,false);
        rcv_SanPhamNoiBat.setLayoutManager(linearLayoutManager2);

        //Sản phẩm moi ra
        rcv_SanPhamMoiRa = mView.findViewById(R.id.rcv_SanPhamMoiRa);
        SanPhamMoiRaAdapter sanPhamMoiRaAdapter= new SanPhamMoiRaAdapter(dbHelper,mView.getContext(),"new", getActivity());
        rcv_SanPhamMoiRa.setAdapter(sanPhamMoiRaAdapter);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(mView.getContext(),LinearLayoutManager.HORIZONTAL,false);
        rcv_SanPhamMoiRa.setLayoutManager(linearLayoutManager1);

        //Sản phẩm
        rcv_SanPham = mView.findViewById(R.id.rcv_SanPham);
        SanPhamAdapter sanPhamAdapter= new SanPhamAdapter(getContext(), dbHelper, getActivity());
        rcv_SanPham.setAdapter(sanPhamAdapter);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mainActivity,2);
        rcv_SanPham.setLayoutManager(gridLayoutManager);

        return mView;

    }

    private List<Listproduct> getProduct() {
        List<Listproduct> list = new ArrayList<>();
        list.add(new Listproduct(R.drawable.ic_17, "Trái cây"));
        list.add(new Listproduct(R.drawable.ic_18, "Rau"));
        list.add(new Listproduct(R.drawable.ic_19, "Củ"));
        list.add(new Listproduct(R.drawable.ic_37, "Gạo"));
        list.add(new Listproduct(R.drawable.ic_30, "Sữa"));
        list.add(new Listproduct(R.drawable.ic_31, "Nấm"));
        list.add(new Listproduct(R.drawable.ic_32, "Đậu"));
        list.add(new Listproduct(R.drawable.ic_33, "Gia vị"));
        list.add(new Listproduct(R.drawable.ic_34, "Đồ khô"));

        return list;
    }
    private void replaceFragment (Fragment fragment) {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, fragment);
        transaction.commit();
    }


    @Override
    public void btnclick(Listproduct listproduct) {
        Bundle bundle = new Bundle();
        bundle.putString("namecate",listproduct.getTitle());
        ProByCateFragment proByCateFragment = new ProByCateFragment();
        proByCateFragment.setArguments(bundle);
        replaceFragment(proByCateFragment);
    }
}

