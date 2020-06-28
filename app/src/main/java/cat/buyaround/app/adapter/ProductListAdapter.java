package cat.buyaround.app.adapter;

import android.content.Context;
import android.text.TextUtils;
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
import cat.buyaround.app.model.Product;
import cat.buyaround.app.utils.Utils;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ViewHolder> {

    private Context context;
    private IAddItemCallback callback;
    private Product[] products;

    public ProductListAdapter(Context context, IAddItemCallback callback) {
        this.context = context;
        this.callback = callback;
        this.products = null;
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
        Product product = products[position];

        holder.itemView.setOnClickListener(view -> callback.onItemSelected(product));
        holder.btnAdd.setOnClickListener(view -> callback.onAddItemTo(product));

        holder.tvTitle.setText(product.getName());
        holder.tvSubtitle.setText(product.getStore().getName());

        holder.tvRating.setText(Utils.floatToString(product.getRating()));
        holder.tvPrice.setText(Utils.floatToString(product.getPrice()));

        if (!TextUtils.isEmpty(product.getImages()[0])) {
            CircularProgressDrawable circularProgressDrawable = new CircularProgressDrawable(holder.itemView.getContext());
            circularProgressDrawable.setStyle(CircularProgressDrawable.LARGE);
            circularProgressDrawable.start();

            Glide.with(context)
                    .asBitmap()
                    .placeholder(circularProgressDrawable)
                    .load(product.getImages()[0])
                    .into(holder.imgView);
        } else {
            Glide.with(context)
                    .load(R.drawable.ic_thumbnail)
                    .into(holder.imgView);
        }
    }

    public void setProducts(Product[] products) {
        this.products = products;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return products != null ? products.length : 0;
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
