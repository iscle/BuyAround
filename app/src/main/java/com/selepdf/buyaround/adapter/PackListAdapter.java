package com.selepdf.buyaround.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.selepdf.buyaround.R;
import com.selepdf.buyaround.adapter.callback.IListAdapter;
import com.selepdf.buyaround.model.Pack;

public class PackListAdapter extends RecyclerView.Adapter<PackListAdapter.ViewHolder> {

    private Context mContext;
    private IListAdapter mCallback;
    private Pack[] mItems;

    public PackListAdapter(Context context, IListAdapter callback) {
        mContext = context;
        mCallback = callback;
        mItems = null;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product,parent, false);
        return new PackListAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Pack pack = mItems[position];
        holder.itemView.setOnClickListener(view -> mCallback.onItemSelected(pack));
        holder.tvTitle.setText(pack.getName());
        holder.tvSubtitle.setText(pack.getDescription());
        holder.tvRating.setText(String.valueOf(pack.getRating()));
        if (pack.getThumbnail() != null) {
            Glide.with(mContext)
                    .asBitmap()
                    .placeholder(R.drawable.ic_thumbnail)
                    .load(pack.getThumbnail())
                    .into(holder.imgView);
        } else {
            Glide.with(mContext)
                    .load("https://www.plasticboxshop.co.uk/images/pack-of-5-eco-archive-cardboard-boxes-p4817-13301_zoom.jpg")
                    .into(holder.imgView);
        }
    }

    public void setPacks(Pack[] packs) {
        this.mItems = packs;
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
