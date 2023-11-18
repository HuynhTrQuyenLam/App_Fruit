package com.example.pjvthl.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pjvthl.DBHelper;
import com.example.pjvthl.R;
import com.example.pjvthl.Utils;
import com.example.pjvthl.model.ProductDB;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Searchadapter extends RecyclerView.Adapter<Searchadapter.searchvh> implements Filterable {
    DBHelper dbHelper;

    ArrayList<ProductDB> listpro1;
    ArrayList<ProductDB> listpro2;
    Context context;

    public Searchadapter(DBHelper dbHelper, ArrayList<ProductDB> listpro, Context context) {
        this.dbHelper = dbHelper;
        this.listpro1 = listpro;
        this.listpro2 = listpro1;
        this.context = context;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String strSearch = constraint.toString();
                if(strSearch.isEmpty()){
                    listpro2 = listpro1;
                }else {
                    List<ProductDB> list = new ArrayList<>();
                    for (ProductDB productDB : listpro2){
                        if (productDB.getNamepro().toLowerCase().contains(strSearch.toLowerCase())||
                                productDB.getImages().contains(constraint)){
                            list.add(productDB);
                        }
                    }
                    listpro1 = (ArrayList<ProductDB>) list;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = listpro1;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                listpro1 = (ArrayList<ProductDB>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    @NonNull
    @Override
    public searchvh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search,parent,false);
        return new searchvh(view);
    }

    @Override
    public void onBindViewHolder(@NonNull searchvh holder, int position) {
        ProductDB productDB = listpro1.get(position);
        Bitmap bitmap = Utils.converttoBitmapFromAsset(context, productDB.getImages());
        holder.imgProduct.setImageBitmap(bitmap);
        holder.tvTitle.setText(productDB.getNamepro());
        holder.tvPrice.setText(productDB.getPrice().toString());
    }

    @Override
    public int getItemCount() {
        if (listpro1 != null){
            return listpro1.size();
        }
        return 0;
    }

    public class searchvh extends RecyclerView.ViewHolder{
        private ImageView imgProduct;
        private TextView tvTitle;
        private TextView tvPrice;

        public searchvh(@NonNull View itemView) {
            super(itemView);
            imgProduct = itemView.findViewById(R.id.img_product);
            tvTitle = itemView.findViewById(R.id.title_product);
            tvPrice = itemView.findViewById(R.id.price_product);
        }
    }
}
