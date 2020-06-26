package cat.buyaround.app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.bumptech.glide.Glide;

import cat.buyaround.app.R;
import cat.buyaround.app.adapter.callback.IAddItemCallback;
import cat.buyaround.app.databinding.ItemProductBinding;
import cat.buyaround.app.model.Pack;
import cat.buyaround.app.utils.Utils;

public class PackListAdapter extends RecyclerView.Adapter<PackListAdapter.ViewHolder> {

    private Context context;
    private IAddItemCallback callback;
    private Pack[] packs;

    public PackListAdapter(Context context, IAddItemCallback callback) {
        this.context = context;
        this.callback = callback;
        this.packs = null;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemProductBinding binding =
                ItemProductBinding.inflate(LayoutInflater.from(parent.getContext()),
                        parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Pack pack = packs[position];

        holder.itemView.setOnClickListener(view -> callback.onItemSelected(pack));
        holder.btnAdd.setOnClickListener(view -> callback.onAddItemTo(pack));

        holder.tvTitle.setText(pack.getName());
        holder.tvSubtitle.setText(pack.getDescription());

        holder.tvRating.setText(String.valueOf(pack.getRating()));
        holder.tvPrice.setText(Utils.floatToString(pack.getPrice()));

        if (pack.getThumbnail() != null) {
            CircularProgressDrawable circularProgressDrawable = new CircularProgressDrawable(holder.itemView.getContext());
            circularProgressDrawable.setStyle(CircularProgressDrawable.LARGE);
            circularProgressDrawable.start();

            Glide.with(context)
                    .asBitmap()
                    .placeholder(circularProgressDrawable)
                    .load(pack.getThumbnail())
                    .into(holder.imgView);
        } else {
            Glide.with(context)
                    .load(R.drawable.ic_thumbnail)
                    .into(holder.imgView);
        }
    }

    public void setPacks(Pack[] packs) {
        this.packs = packs;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return packs != null ? packs.length : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgView;
        TextView tvTitle, tvSubtitle, tvRating, tvPrice;
        Button btnAdd;

        ViewHolder(@NonNull ItemProductBinding binding) {
            super(binding.getRoot());
            imgView = binding.itemProductImg;
            tvTitle = binding.itemProductTitle;
            tvSubtitle = binding.itemProductSubtitle;
            tvRating = binding.itemProductRating;
            tvPrice = binding.priceTv;
            btnAdd = binding.itemProductAddBtn;
        }
    }
}
