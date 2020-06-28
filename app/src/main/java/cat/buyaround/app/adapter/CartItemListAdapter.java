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
import cat.buyaround.app.model.Item;
import cat.buyaround.app.model.ItemGroup;

public class CartItemListAdapter extends RecyclerView.Adapter<CartItemListAdapter.ViewHolder> {

    private List<ItemGroup> mProducts;
    private IListAdapter mCallback;

    public CartItemListAdapter(List<ItemGroup> products, IListAdapter callback) {
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
        ItemGroup itemGroup = mProducts.get(position);
        Item item = itemGroup.getItem();

        CircularProgressDrawable circularProgressDrawable = new CircularProgressDrawable(holder.itemView.getContext());
        circularProgressDrawable.setStyle(CircularProgressDrawable.LARGE);
        circularProgressDrawable.start();

        Glide.with(holder.context)
                .asBitmap()
                .placeholder(circularProgressDrawable)
                .load(item.getImages()[0])
                .into(holder.photoIv);

        holder.nameTv.setText(item.getName());
        holder.priceTv.setText(String.valueOf(itemGroup.getTotalPrice()));
        holder.quantityTv.setText(itemGroup.getQuantity());


        if (itemGroup.getQuantity() == 1) {
            holder.substractBtn.setImageDrawable(
                    holder.context.getResources().getDrawable(R.drawable.ic_remove_cart));
            holder.substractBtn.setColorFilter(ContextCompat.getColor(holder.context, R.color.colorError));
        }

        holder.addBtn.setOnClickListener(v -> {
            // TODO: API CALL
            itemGroup.addItem();
            holder.priceTv.setText(String.valueOf(itemGroup.getTotalPrice()));
            holder.quantityTv.setText(itemGroup.getQuantity());
        });

        holder.substractBtn.setOnClickListener(v -> {
            // TODO: IF QUANTITY = 1, DELETE PRODUCT, ELSE:
            // TODO: API CALL
            itemGroup.substractItem();
            holder.priceTv.setText(String.valueOf(itemGroup.getTotalPrice()));
            holder.quantityTv.setText(itemGroup.getQuantity());

            if (itemGroup.getQuantity() == 1) {
                holder.substractBtn.setImageDrawable(
                        holder.context.getResources().getDrawable(R.drawable.ic_remove_cart));
                holder.substractBtn.setColorFilter(ContextCompat.getColor(holder.context, R.color.colorError));
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
