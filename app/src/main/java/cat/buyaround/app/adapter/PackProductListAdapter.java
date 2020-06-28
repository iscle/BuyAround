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

import cat.buyaround.app.adapter.callback.IListAdapter;
import cat.buyaround.app.databinding.ItemProductInPackBinding;
import cat.buyaround.app.model.ItemGroup;
import cat.buyaround.app.model.Product;

public class PackProductListAdapter extends RecyclerView.Adapter<PackProductListAdapter.ViewHolder> {

    private ItemGroup[] mProducts;
    private IListAdapter mCallback;

    public PackProductListAdapter(ItemGroup[] products, IListAdapter callback) {
        mProducts = products;
        mCallback = callback;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemProductInPackBinding binding =
                ItemProductInPackBinding.inflate(LayoutInflater.from(parent.getContext()),
                        parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = (Product) mProducts[position].getItem();

        CircularProgressDrawable circularProgressDrawable = new CircularProgressDrawable(holder.itemView.getContext());
        circularProgressDrawable.setStyle(CircularProgressDrawable.LARGE);
        circularProgressDrawable.start();

        Glide.with(holder.context)
                .asBitmap()
                .placeholder(circularProgressDrawable)
                .load(product.getImages()[0])
                .into(holder.photoIv);

        holder.nameTv.setText(product.getName());
        holder.priceTv.setText(String.valueOf(product.getPrice()));
        holder.quantityTv.setText(mProducts[position].getQuantity());
    }

    @Override
    public int getItemCount() {
        return mProducts != null ? mProducts.length : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView photoIv;
        TextView nameTv, priceTv, quantityTv;
        Context context;

        ViewHolder(ItemProductInPackBinding binding) {
            super(binding.getRoot());
            photoIv = binding.productIv;
            nameTv = binding.productName;
            priceTv = binding.productPrice;
            quantityTv = binding.productQuantity;

            context = binding.getRoot().getContext();
        }
    }
}
