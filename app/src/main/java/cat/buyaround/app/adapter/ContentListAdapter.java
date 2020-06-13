package cat.buyaround.app.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cat.buyaround.app.R;
import cat.buyaround.app.adapter.callback.IListAdapter;

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
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_content, parent, false);
        return new ContentListAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ContentListAdapter.ViewHolder holder, int position) {
        holder.itemView.setOnClickListener(view -> mCallback.onItemSelected(mItems.get(position)));
        holder.tvTitle.setText(mItems.get(position));
    }

    @Override
    public int getItemCount() {
        return mItems != null ? mItems.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.item_content_title);
        }
    }
}