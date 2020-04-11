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
import com.selepdf.hackovid.model.Product;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ViewHolder> {

    private Context mContext;
    private IListAdapter mCallback;
    private Product[] mItems;

    public ProductListAdapter(Context context, IListAdapter callback) {
        mContext = context;
        mCallback = callback;
        mItems = null;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product,parent, false);
        return new ProductListAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = mItems[position];
        holder.itemView.setOnClickListener(view -> mCallback.onItemSelected(product));
        holder.tvTitle.setText(product.getName());
        holder.tvSubtitle.setText(product.getDescription());
        holder.tvRating.setText(String.valueOf(product.getRating()));
        if (product.getThumbnail() != null) {
            Glide.with(mContext)
                    .asBitmap()
                    .placeholder(R.drawable.ic_thumbnail)
                    .load(product.getThumbnail())
                    .into(holder.imgView);
        } else {
            Glide.with(mContext)
                    .load("https://pics.drugstore.com/prodimg/553160/900.jpg")
                    .into(holder.imgView);
        }
    }

    public void setProducts(Product[] products) {
        this.mItems = products;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mItems != null ? mItems.length : 0;
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
