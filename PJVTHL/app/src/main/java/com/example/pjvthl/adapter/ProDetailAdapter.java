package com.example.pjvthl.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pjvthl.DBHelper;
import com.example.pjvthl.R;
import com.example.pjvthl.Utils;
import com.example.pjvthl.model.Listproduct;
import com.example.pjvthl.model.ProductDB;

import java.util.List;

public class ProDetailAdapter extends RecyclerView.Adapter<ProDetailAdapter.ProDetailViewHolder>{
    DBHelper dbHelper;
    Context context;
    private List<Listproduct> mListproduct;
    ListproductAdapter.Listener listener;

    public void setData(List<Listproduct> list, ListproductAdapter.Listener listener){
        this.mListproduct = list;
        this.listener = listener;
        notifyDataSetChanged();
    }
    public ProDetailAdapter(DBHelper dbHelper, Context context ) {
        this.dbHelper = dbHelper;
        this.context = context;
    }

    @NonNull
    @Override
    public ProDetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_product_detail,parent,false);
        return new ProDetailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProDetailViewHolder holder, int position) {
        ProductDB productDB = dbHelper.getproduct().get(position);
        holder.imgProduct.setImageBitmap(Utils.converttoBitmapFromAsset(context,productDB.getImages()));
        holder.tvTitle.setText(productDB.getNamepro());
        holder.tvPrice.setText(productDB.getPrice().toString());
        holder.tvQuantity.setText(productDB.getQuantity().toString());
        holder.tvDecs.setText(productDB.getDecs());
        holder.addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int i = 1;
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

    @Override
    public int getItemCount() {
        return dbHelper.getprodetail().size();
    }

    public class ProDetailViewHolder extends RecyclerView.ViewHolder{
        private ImageView imgProduct;
        private TextView tvTitle;
        private TextView tvPrice;
        private TextView tvQuantity;
        private TextView tvDecs;
        private Button addtocart;

        public ProDetailViewHolder(@NonNull View itemView) {
            super(itemView);
            imgProduct = itemView.findViewById(R.id.hinhsanpham);
            tvTitle = itemView.findViewById(R.id.tensanpham);
            tvPrice = itemView.findViewById(R.id.textviewgia);
            tvQuantity = itemView.findViewById(R.id.soluongkho);
            tvDecs = itemView.findViewById(R.id.mota);
            addtocart = itemView.findViewById(R.id.btn_themgiohang);

        }
    }

    public interface Listener{
        void btnclick(Listproduct listproduct);
    }
}
