package com.example.pjvthl.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pjvthl.DBHelper;
import com.example.pjvthl.R;
import com.example.pjvthl.Utils;
import com.example.pjvthl.model.CartDb;

public class ProbyCateAdapter extends RecyclerView.Adapter<ProbyCateAdapter.ProByCateVH>{
    DBHelper dbHelper;

    Context context;
    String cate;
    public ProbyCateAdapter(DBHelper dbHelper, Context context,String cate ) {
        this.dbHelper = dbHelper;
        this.context = context;
        this.cate = cate;
    }


    @NonNull
    @Override
    public ProbyCateAdapter.ProByCateVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sanpham,parent,false);
        return new ProbyCateAdapter.ProByCateVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProByCateVH holder, int position) {
        CartDb cartDb = dbHelper.getitembycate(cate).get(position);
        holder.img.setImageBitmap(Utils.converttoBitmapFromAsset(context,cartDb.getImages()));
        holder.name.setText(cartDb.getName());
        holder.price.setText(cartDb.getPrice().toString());

    }

    @Override
    public int getItemCount() {
        return dbHelper.getitembycate(cate).size();
    }

    //Ánh xạ
    public class ProByCateVH extends RecyclerView.ViewHolder{
        ImageView img;
        TextView name;
        TextView price;

        public ProByCateVH(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img_product);
            name = itemView.findViewById(R.id.title_product);
            price = itemView.findViewById(R.id.price_product);
        }
    }
}
