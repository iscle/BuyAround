package cat.buyaround.app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import cat.buyaround.app.R;

public class ImageViewPagerAdapter extends RecyclerView.Adapter<ImageViewPagerAdapter.ImageViewHolder> {
    private Context context;
    private String[] images;

    public ImageViewPagerAdapter(Context context, String[] images) {
        this.context = context;
        this.images = images;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_image_viewpager, parent, false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
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
        return images == null? 0 : images.length;
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {
        ImageView itemIv;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            itemIv = itemView.findViewById(R.id.image_iv);
        }
    }
}
