package cat.buyaround.app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.bumptech.glide.Glide;

import cat.buyaround.app.R;
import cat.buyaround.app.adapter.callback.IListAdapter;
import cat.buyaround.app.databinding.ItemStoreBinding;
import cat.buyaround.app.model.Store;

public class StoreListAdapter extends RecyclerView.Adapter<StoreListAdapter.ViewHolder> {

    private Context context;
    private IListAdapter callback;
    private Store[] stores;

    public StoreListAdapter(Context context, IListAdapter callback) {
        this.context = context;
        this.callback = callback;
        this.stores = null;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemStoreBinding binding =
                ItemStoreBinding.inflate(LayoutInflater.from(parent.getContext()),
                        parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Store store = stores[position];

        holder.itemView.setOnClickListener(view -> callback.onItemSelected(store));

        holder.tvTitle.setText(store.getName());
        holder.tvSubtitle.setText(store.getDescription());
        holder.tvRating.setText(String.valueOf(store.getRating()));

        if (store.getThumbnail() != null) {
            CircularProgressDrawable circularProgressDrawable = new CircularProgressDrawable(holder.itemView.getContext());
            circularProgressDrawable.setStrokeWidth(5);
            circularProgressDrawable.setCenterRadius(30);
            circularProgressDrawable.start();

            Glide.with(context)
                    .asBitmap()
                    .placeholder(circularProgressDrawable)
                    .load(store.getThumbnail())
                    .into(holder.imgView);
        } else {
            Glide.with(context)
                    .asBitmap()
                    .load(R.drawable.ic_thumbnail)
                    .into(holder.imgView);
        }
    }

    public void setStores(Store[] stores) {
        this.stores = stores;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return stores != null ? stores.length : 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgView;
        TextView tvTitle, tvSubtitle, tvRating;

        ViewHolder(ItemStoreBinding binding) {
            super(binding.getRoot());
            imgView = binding.itemStoreImg;
            tvTitle = binding.itemStoreTitle;
            tvSubtitle = binding.itemStoreSubtitle;
            tvRating = binding.itemStoreRating;
        }
    }
}
