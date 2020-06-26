package cat.buyaround.app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.bumptech.glide.Glide;

import java.util.List;

import cat.buyaround.app.R;
import cat.buyaround.app.adapter.callback.IListAdapter;
import cat.buyaround.app.databinding.ItemCartOrderBinding;
import cat.buyaround.app.model.OrderProduct;

public class CartItemListAdapter extends RecyclerView.Adapter<CartItemListAdapter.ViewHolder> {

    private List<OrderProduct> mProducts;
    private IListAdapter mCallback;

    public CartItemListAdapter(List<OrderProduct> products, IListAdapter callback) {
        mProducts = products;
        mCallback = callback;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCartOrderBinding binding =
                ItemCartOrderBinding.inflate(LayoutInflater.from(parent.getContext()),
                        parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        OrderProduct product = mProducts.get(position);

        CircularProgressDrawable circularProgressDrawable = new CircularProgressDrawable(holder.itemView.getContext());
        circularProgressDrawable.setStyle(CircularProgressDrawable.LARGE);
        circularProgressDrawable.start();

        Glide.with(holder.context)
                .asBitmap()
                .placeholder(circularProgressDrawable)
                .load(product.getThumbnail())
                .into(holder.photoIv);

        holder.nameTv.setText(product.getName());
        holder.priceTv.setText(String.valueOf(product.getPrice()));
        holder.quantityTv.setText(product.getQuantity());


        if (product.getQuantity() == 1) {
            holder.substractBtn.setImageDrawable(
                    holder.context.getResources().getDrawable(R.drawable.ic_remove_cart));
            holder.substractBtn.setColorFilter(ContextCompat.getColor(holder.context, R.color.colorError));
        }

        holder.addBtn.setOnClickListener(v -> {
            product.setQuantity(product.getQuantity() + 1);
        });

        holder.substractBtn.setOnClickListener(v -> {
            if (product.getQuantity() > 1) {
                product.setQuantity(product.getQuantity() - 1);
                if (product.getQuantity() == 1) {
                    holder.substractBtn.setImageDrawable(
                            holder.context.getResources().getDrawable(R.drawable.ic_remove_cart));
                    holder.substractBtn.setColorFilter(ContextCompat.getColor(holder.context, R.color.colorError));
                }
            } else {
                //TODO: DELETE PRODUCT
            }
        });
    }

    @Override
    public int getItemCount() {
        return mProducts != null ? mProducts.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView photoIv;
        TextView nameTv, priceTv, quantityTv;
        ImageButton addBtn, substractBtn;
        Context context;

        ViewHolder(ItemCartOrderBinding binding) {
            super(binding.getRoot());
            photoIv = binding.productIv;
            nameTv = binding.productName;
            priceTv = binding.productPrice;
            quantityTv = binding.productQuantity;
            addBtn = binding.addBtn;
            substractBtn = binding.substractBtn;
            context = binding.getRoot().getContext();
        }
    }
}
