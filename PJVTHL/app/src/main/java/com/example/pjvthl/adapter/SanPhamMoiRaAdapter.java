package com.example.pjvthl.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pjvthl.DBHelper;
import com.example.pjvthl.R;
import com.example.pjvthl.Utils;
import com.example.pjvthl.fragment.ProDetailFragment;
import com.example.pjvthl.model.ProductDB;

import java.io.Serializable;


public class SanPhamMoiRaAdapter extends RecyclerView.Adapter<SanPhamMoiRaAdapter.SanPhamMoiRaViewHolder> {

    DBHelper dbHelper;

    Context context;
    String prokind;
    FragmentActivity macti;
    public Integer i = 0;
    public SanPhamMoiRaAdapter(DBHelper dbHelper, Context context, String prokind, FragmentActivity activity) {
        this.dbHelper = dbHelper;
        this.context = context;
        this.prokind = prokind;
        this.macti =activity;
    }

    @NonNull
    @Override
    public SanPhamMoiRaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sanphammoira,parent,false);
        return new SanPhamMoiRaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SanPhamMoiRaViewHolder holder, int position) {
        if(prokind=="new"){
            ProductDB productDB = dbHelper.getproductnew().get(position);
            holder.imgProduct.setImageBitmap(Utils.converttoBitmapFromAsset(context,productDB.getImages()));
            holder.imgProduct.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onimgclick(productDB);
                }
            });
            holder.tvTitle.setText(productDB.getNamepro());
            holder.tvPrice.setText(productDB.getPrice().toString());
            holder.btnaddtocart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    i+=1;
                    if(dbHelper.itemcartexits(productDB)==false){
                        dbHelper.inserttocart(productDB);
                        Toast.makeText(context, "Đã thêm vào giỏ hàng", Toast.LENGTH_SHORT).show();
                        dbHelper.updatequantity(i,productDB);
                    }
                    else
                    {
                        Toast.makeText(context, "Đã thêm vào giỏ hàng", Toast.LENGTH_SHORT).show();
                        dbHelper.updatequantity(dbHelper.getQuantity()+1,productDB);

                    }
                }
            });
        }
        if(prokind=="outstanding"){
            ProductDB productDB = dbHelper.getproductoutstanding().get(position);
            holder.imgProduct.setImageBitmap(Utils.converttoBitmapFromAsset(context,productDB.getImages()));
            holder.imgProduct.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onimgclick(productDB);
                }
            });
            holder.tvTitle.setText(productDB.getNamepro());
            holder.tvPrice.setText(productDB.getPrice().toString());
            holder.btnaddtocart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    i+=1;
                    if(dbHelper.itemcartexits(productDB)==false){
                        dbHelper.inserttocart(productDB);
                        Toast.makeText(context, "Đã thêm vào giỏ hàng", Toast.LENGTH_SHORT).show();
                        dbHelper.updatequantity(i,productDB);
                    }
                    else
                    {
                        Toast.makeText(context, "Đã thêm vào giỏ hàng", Toast.LENGTH_SHORT).show();
                        dbHelper.updatequantity(dbHelper.getQuantity()+1,productDB);

                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if(prokind=="new")
            return dbHelper.getproductnew().size();
        if(prokind=="outstanding")
            return dbHelper.getproductoutstanding().size();
        return 0;
    }

    //Ánh xạ
    public class SanPhamMoiRaViewHolder extends RecyclerView.ViewHolder{
        private ImageView imgProduct;
        private TextView tvTitle;
        private TextView tvPrice;
        private TextView tvQuantity;
        private TextView tvDecs;
        ImageButton btnaddtocart;

        public SanPhamMoiRaViewHolder(@NonNull View itemView) {
            super(itemView);
            imgProduct = itemView.findViewById(R.id.img_sanphamnew);
            tvTitle = itemView.findViewById(R.id.title_sanphamnew);
            tvPrice = itemView.findViewById(R.id.price_sanphamnew);
            btnaddtocart = itemView.findViewById(R.id.btnaddtocart);
        }
    }
    private void replaceFragment (Fragment fragment) {
        FragmentTransaction transaction = macti.getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, fragment);
        transaction.commit();
    }
    public void onimgclick(ProductDB productDB){
        Bundle bundle = new Bundle();
        bundle.putSerializable("spdetail",productDB);
        ProDetailFragment proDetailFragment = new ProDetailFragment();
        proDetailFragment.setArguments(bundle);
        replaceFragment(proDetailFragment);
    }
}
