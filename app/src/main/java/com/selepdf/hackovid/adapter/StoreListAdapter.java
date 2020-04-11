package com.selepdf.hackovid.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.selepdf.hackovid.R;
import com.selepdf.hackovid.adapter.callback.IListAdapter;
import com.selepdf.hackovid.databinding.ItemProductBinding;
import com.selepdf.hackovid.model.Store;

public class StoreListAdapter extends RecyclerView.Adapter<StoreListAdapter.ViewHolder> {

    private Context mContext;
    private IListAdapter mCallback;
    private Store[] mItems;

    public StoreListAdapter(Context context, IListAdapter callback) {
        mContext = context;
        mCallback = callback;
        mItems = null;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemProductBinding binding =
                ItemProductBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Store store = mItems[position];
        holder.itemView.setOnClickListener(view -> mCallback.onItemSelected(store));
        holder.tvTitle.setText(store.getName());
        if (store.getDirection() != null)
            holder.tvSubtitle.setText(store.getDirection().getAddress());
        holder.tvRating.setText(String.valueOf(store.getRating()));
        if (store.getThumbnail() != null) {
            Glide.with(mContext)
                    .asBitmap()
                    .placeholder(R.drawable.ic_thumbnail)
                    .load(store.getThumbnail())
                    .into(holder.imgView);
        } else {
            Glide.with(mContext)
                    .asBitmap()
                    .load(R.drawable.ic_thumbnail)
                    .into(holder.imgView);
        }
    }

    public void setStores(Store[] stores) {
        this.mItems = stores;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mItems != null ? mItems.length : 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgView;
        TextView tvTitle, tvSubtitle, tvRating;

        ViewHolder(ItemProductBinding binding) {
            super(binding.getRoot());
            imgView = binding.itemProductImg;
            tvTitle = binding.itemProductTitle;
            tvSubtitle = binding.itemProductSubtitle;
            tvRating = binding.itemProductRating;
        }
    }
}
