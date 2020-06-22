package cat.buyaround.app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import cat.buyaround.app.R;
import cat.buyaround.app.databinding.ItemImageViewpagerBinding;

public class ImageViewPagerAdapter extends RecyclerView.Adapter<ImageViewPagerAdapter.ViewHolder> {
    private Context context;
    private String[] images;

    public ImageViewPagerAdapter(Context context, String[] images) {
        this.context = context;
        this.images = images;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemImageViewpagerBinding binding =
                ItemImageViewpagerBinding.inflate(LayoutInflater.from(parent.getContext()),
                        parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (images[position] != null) {
            Glide.with(context)
                    .asBitmap()
                    .placeholder(R.drawable.ic_thumbnail)
                    .load(images[position])
                    .into(holder.itemIv);
        } else {
            Glide.with(context)
                    .load(R.drawable.ic_thumbnail)
                    .into(holder.itemIv);
        }
    }

    @Override
    public int getItemCount() {
        return images == null ? 0 : images.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView itemIv;

        ViewHolder(ItemImageViewpagerBinding binding) {
            super(binding.getRoot());
            itemIv = binding.imageIv;
        }
    }
}
