package cat.buyaround.app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import cat.buyaround.app.adapter.callback.IListAdapter;
import cat.buyaround.app.databinding.ItemNotificationBinding;
import cat.buyaround.app.model.Notification;

public class NotificationListAdapter extends RecyclerView.Adapter<NotificationListAdapter.ViewHolder> {

    private Context mContext;
    private IListAdapter mCallback;
    private Notification[] mNotifications;

    public NotificationListAdapter(Context context, IListAdapter callback) {
        mContext = context;
        mCallback = callback;
        mNotifications = null;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemNotificationBinding binding =
                ItemNotificationBinding.inflate(LayoutInflater.from(parent.getContext()),
                        parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Notification notification = mNotifications[position];
        holder.itemView.setOnClickListener(view -> mCallback.onItemSelected(notification));
        holder.tvTime.setText(notification.getMinutesLeft());
    }

    public void setNotifications(Notification[] notifications) {
        mNotifications = notifications;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mNotifications != null ? mNotifications.length : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvTime;

        ViewHolder(ItemNotificationBinding binding) {
            super(binding.getRoot());
            tvTime = binding.itemNotificationTime;
        }
    }
}
