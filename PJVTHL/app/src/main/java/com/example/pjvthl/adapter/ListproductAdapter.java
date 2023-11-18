package com.example.pjvthl.adapter;

import android.location.GnssAntennaInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pjvthl.R;
import com.example.pjvthl.model.Listproduct;

import java.util.List;


public class ListproductAdapter extends RecyclerView.Adapter<ListproductAdapter.LisProductViewHolder>{

   private List<Listproduct> mListproduct;

   Listener listener;
   public void setData(List<Listproduct> list,Listener listener){
       this.mListproduct = list;
       this.listener = listener;
       notifyDataSetChanged();
   }

    @NonNull
    @Override
    public LisProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_listproduct,parent,false);
       return new LisProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LisProductViewHolder holder, int position) {
        Listproduct listproduct = mListproduct.get(position);
        if (listproduct == null){
            return;
        }
        holder.imgListProduct.setImageResource(listproduct.getResoureId());
        holder.tvProductName.setText(listproduct.getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.btnclick(listproduct);
            }
        });


    }

    @Override
    public int getItemCount() {
       if (mListproduct != null) {
           return mListproduct.size();
       }
        return 0;
    }

//Ánh xạ
    public class LisProductViewHolder extends RecyclerView.ViewHolder{
        private ImageView imgListProduct;
        private TextView tvProductName;

        public LisProductViewHolder(@NonNull View itemView) {
            super(itemView);
            imgListProduct = itemView.findViewById(R.id.icon_list);
            tvProductName = itemView.findViewById(R.id.title_list);
        }
    }
    public interface Listener{
       void btnclick(Listproduct listproduct);
    }

}
