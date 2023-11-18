package com.example.pjvthl.adapter;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pjvthl.DBHelper;
import com.example.pjvthl.R;
import com.example.pjvthl.Utils;
import com.example.pjvthl.model.ProductDB;
import com.example.pjvthl.model.SanPham;
import com.example.pjvthl.model.SanPhamNoiBat;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;


public class SanPhamNoiBatAdapter extends RecyclerView.Adapter<SanPhamNoiBatAdapter.SanPhamNoiBatViewHolder> {
    DBHelper dbHelper;
    Context context;
    String prokind;

    public Integer i = 0;

    public static Bitmap converttoBitmapFromAsset(Context context, String imgname){
        AssetManager assetManager = context.getAssets();
        try{
            InputStream inputStream = assetManager.open(imgname);
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            return bitmap;
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    public SanPhamNoiBatAdapter(Context context, DBHelper dbHelper, String prokind) {
        this.dbHelper = dbHelper;
        this.context = context;
        this.prokind = prokind;

    }


    @NonNull
    @Override
    public SanPhamNoiBatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sanphamnoibat,parent,false);
        return new SanPhamNoiBatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SanPhamNoiBatViewHolder holder, int position) {
        if(prokind=="outstanding"){
            ProductDB productDB = dbHelper.getproduct().get(position);
            holder.imgProduct.setImageBitmap(Utils.converttoBitmapFromAsset(context,productDB.getImages()));
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
        return dbHelper.getproduct().size();
    }

    //Ánh xạ
    public class SanPhamNoiBatViewHolder extends RecyclerView.ViewHolder{
        private ImageView imgProduct;
        private TextView tvTitle;
        private TextView tvPrice;

        private ImageButton btnaddtocart;

        public SanPhamNoiBatViewHolder(@NonNull View itemView) {
            super(itemView);
            imgProduct = itemView.findViewById(R.id.img_product);
            tvTitle = itemView.findViewById(R.id.title_product);
            tvPrice = itemView.findViewById(R.id.price_product);
            btnaddtocart = itemView.findViewById(R.id.btnaddtocart);
        }
    }
}
