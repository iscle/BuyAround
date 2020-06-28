package cat.buyaround.app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import cat.buyaround.app.adapter.callback.IListAdapter;
import cat.buyaround.app.databinding.ItemOrderBinding;
import cat.buyaround.app.model.Order;

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
        ItemOrderBinding binding =
                ItemOrderBinding.inflate(LayoutInflater.from(parent.getContext()),
                        parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Order order = mOrders[position];
        holder.itemView.setOnClickListener(view -> mCallback.onItemSelected(order));
        holder.tvTitle.setText("1");
        holder.tvShop.setText("1");
        holder.tvCost.setText("1");
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

        ViewHolder(ItemOrderBinding binding) {
            super(binding.getRoot());
            tvTitle = binding.itemOrderTitle;
            tvShop = binding.itemOrderShop;
            tvCost = binding.itemOrderCost;
        }
    }
}
