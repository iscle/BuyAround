package cat.buyaround.app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import cat.buyaround.app.R;
import cat.buyaround.app.adapter.callback.IListAdapter;
import cat.buyaround.app.databinding.ItemCompactStoreBinding;
import cat.buyaround.app.model.Store;

public class CompactStoreListAdapter extends RecyclerView.Adapter<CompactStoreListAdapter.ViewHolder> {

    private Context mContext;
    private IListAdapter mCallback;
    private Store[] mItems;

    public CompactStoreListAdapter(Context context, IListAdapter callback) {
        mContext = context;
        mCallback = callback;
        mItems = null;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCompactStoreBinding binding =
                ItemCompactStoreBinding.inflate(LayoutInflater.from(parent.getContext()),
                        parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Store store = mItems[position];

        holder.itemView.setOnClickListener(view -> mCallback.onItemSelected(store));

        holder.tvName.setText(store.getName());

        if (store.getDirection() != null)
            holder.tvAddress.setText(store.getDirection().getAddress());

        holder.tvRating.setText(String.valueOf(store.getRating()));

        if (store.getThumbnail() != null) {
            Glide.with(mContext)
                    .asBitmap()
                    .placeholder(R.drawable.ic_thumbnail)
                    .load(store.getThumbnail())
                    .into(holder.imgView);
        } else {
            Glide.with(mContext)
                    .load("https://www.definicionabc.com/wp-content/uploads/tecnologia/App-Store.jpg")
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
        TextView tvName, tvAddress, tvRating;

        ViewHolder(ItemCompactStoreBinding binding) {
            super(binding.getRoot());
            imgView = binding.itemStoreImg;
            tvName = binding.itemStoreName;
            tvAddress = binding.itemStoreAddress;
            tvRating = binding.itemStoreRating;
        }
    }
}
