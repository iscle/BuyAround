package cat.buyaround.app.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cat.buyaround.app.R;
import cat.buyaround.app.adapter.callback.IListAdapter;
import cat.buyaround.app.databinding.ItemContentBinding;

public class ContentListAdapter extends RecyclerView.Adapter<ContentListAdapter.ViewHolder> {

    private IListAdapter mCallback;
    private List<String> mItems;

    public ContentListAdapter(IListAdapter callback, List<String> items) {
        mCallback = callback;
        mItems = items;
    }

    @NonNull
    @Override
    public ContentListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemContentBinding binding =
                ItemContentBinding.inflate(LayoutInflater.from(parent.getContext()),
                        parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ContentListAdapter.ViewHolder holder, int position) {
        holder.tvTitle.setText(mItems.get(position));
        holder.itemView.setOnClickListener(view -> mCallback.onItemSelected(mItems.get(position)));

        if (mItems.get(position).equals("Log out")) {
            holder.tvTitle.setTextColor(Color.parseColor("#B00020"));
            holder.ivIcon.setImageResource(R.drawable.ic_log_out);
            holder.ivIcon.setColorFilter(Color.parseColor("#B00020"));
        }
    }

    @Override
    public int getItemCount() {
        return mItems != null ? mItems.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle;
        ImageView ivIcon;

        ViewHolder(ItemContentBinding binding) {
            super(binding.getRoot());
            tvTitle = binding.itemContentTitle;
            ivIcon = binding.itemContentIcon;
        }
    }
}