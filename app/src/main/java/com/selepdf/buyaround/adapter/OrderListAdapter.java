package com.selepdf.buyaround.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.selepdf.buyaround.R;
import com.selepdf.buyaround.adapter.callback.IListAdapter;
import com.selepdf.buyaround.model.Order;

public class OrderListAdapter extends RecyclerView.Adapter<OrderListAdapter.ViewHolder> {

    private Context mContext;
    private IListAdapter mCallback;
    private Order[] mOrders;

    public OrderListAdapter(Context context, IListAdapter callback) {
        mContext = context;
        mCallback = callback;
        mOrders = null;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order, parent, false);
        return new OrderListAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Order order = mOrders[position];
        holder.itemView.setOnClickListener(view -> mCallback.onItemSelected(order));
        holder.tvTitle.setText(order.getId());
        holder.tvShop.setText(order.getShopId());
        holder.tvCost.setText(String.valueOf(order.getPrice()));
    }

    public void setOrders(Order[] orders) {
        mOrders = orders;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mOrders != null ? mOrders.length : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle;
        TextView tvShop;
        TextView tvCost;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.item_order_title);
            tvShop = itemView.findViewById(R.id.item_order_shop);
            tvCost = itemView.findViewById(R.id.item_order_cost);
        }
    }
}
