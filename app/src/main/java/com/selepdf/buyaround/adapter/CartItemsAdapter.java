package com.selepdf.buyaround.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.selepdf.buyaround.R;
import com.selepdf.buyaround.adapter.callback.IListAdapter;
import com.selepdf.buyaround.model.OrderProduct;
import com.selepdf.buyaround.model.Product;

import java.util.List;

public class CartItemsAdapter extends RecyclerView.Adapter<CartItemsAdapter.ViewHolder> {

    private List<OrderProduct> mProducts;
    private IListAdapter mCallback;

    public CartItemsAdapter(List<OrderProduct> products, IListAdapter callback) {
        mProducts = products;
        mCallback = callback;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart_order,parent);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvTitle.setText(mProducts.get(position).getName());
        holder.tvPrice.setText(Float.toString(mProducts.get(position).getPrice()));
        holder.tvQuantity.setText("x" + mProducts.get(position).getQuantity());
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCallback.onItemSelected(mProducts.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mProducts != null ? mProducts.size():0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle, tvPrice, tvQuantity;
        Button btnDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.item_cart_order_title);
            tvPrice = itemView.findViewById(R.id.item_cart_unit_price);
            tvQuantity = itemView.findViewById(R.id.item_cart_order_quantity);
            btnDelete = itemView.findViewById(R.id.item_cart_order_delete);
        }
    }
}
