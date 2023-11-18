package com.example.pjvthl.adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pjvthl.DBHelper;
import com.example.pjvthl.R;
import com.example.pjvthl.Utils;
import com.example.pjvthl.model.CartDb;
import com.example.pjvthl.model.ProductDB;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartVH>{
    DBHelper dbHelper;

    Context context;
    public CartAdapter(DBHelper dbHelper, Context context ) {
        this.dbHelper = dbHelper;
        this.context = context;
    }


    @NonNull
    @Override
    public CartVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cartitem,parent,false);
        return new CartVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartVH holder, int position) {
        CartDb cartDb = dbHelper.getcartitem().get(position);
        holder.img.setImageBitmap(Utils.converttoBitmapFromAsset(context,cartDb.getImages()));
        holder.name.setText(cartDb.getName());
        holder.price.setText(cartDb.getPrice().toString());
        holder.quantity.setText(cartDb.getQuantity().toString());
    }

    @Override
    public int getItemCount() {
        return dbHelper.getcartitem().size();
    }

    //Ánh xạ
    public class CartVH extends RecyclerView.ViewHolder{
        ImageView img;
        TextView name;
        TextView price,quantity;
        Button btnDelete;

        public CartVH(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.imgcart);
            name = itemView.findViewById(R.id.titlecart);
            price = itemView.findViewById(R.id.pricecart);
            quantity = itemView.findViewById(R.id.tvsoluong);
        }
    }
}
