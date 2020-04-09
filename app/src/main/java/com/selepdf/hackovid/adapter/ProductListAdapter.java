package com.selepdf.hackovid.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.selepdf.hackovid.R;
import com.selepdf.hackovid.adapter.callback.IListAdapter;
import com.selepdf.hackovid.model.GeneralItem;
import com.selepdf.hackovid.model.Product;
import com.selepdf.hackovid.model.Store;

import java.util.List;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ViewHolder> {

    private Context mContext;
    private IListAdapter mCallback;
    private List<Product> mItems;

    public ProductListAdapter(Context context, IListAdapter callback, List<Product> items) {
        mContext = context;
        mCallback = callback;
        mItems = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product,parent, false);
        return new ProductListAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.itemView.setOnClickListener(view -> mCallback.onItemSelected(mItems.get(position)));
        holder.tvTitle.setText(mItems.get(position).getName());
        holder.tvSubtitle.setText(mItems.get(position).getDescription());
        holder.tvRating.setText(Float.toString(mItems.get(position).getRating()));
        Glide.with(mContext)
                .asBitmap()
                .placeholder(R.drawable.ic_thumbnail)
                .load(mItems.get(position).getThumbnail())
                .into(holder.imgView);
    }

    @Override
    public int getItemCount() {
        return mItems != null ? mItems.size():0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgView;
        TextView tvTitle, tvSubtitle, tvRating;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgView = itemView.findViewById(R.id.item_product_img);
            tvTitle = itemView.findViewById(R.id.item_product_title);
            tvSubtitle = itemView.findViewById(R.id.item_product_subtitle);
            tvRating = itemView.findViewById(R.id.item_product_rating);
        }
    }
}
