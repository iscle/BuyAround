package cat.buyaround.app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import cat.buyaround.app.R;
import cat.buyaround.app.adapter.callback.IListAdapter;
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
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notification, parent, false);
        return new NotificationListAdapter.ViewHolder(v);
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

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTime = itemView.findViewById(R.id.item_notification_time);
        }
    }
}
