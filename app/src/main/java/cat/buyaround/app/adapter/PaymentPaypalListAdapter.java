package cat.buyaround.app.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import cat.buyaround.app.databinding.ItemPaymentPaypalBinding;

public class PaymentPaypalListAdapter extends RecyclerView.Adapter<PaymentPaypalListAdapter.ViewHolder> {
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemPaymentPaypalBinding binding =
                ItemPaymentPaypalBinding.inflate(LayoutInflater.from(parent.getContext()),
                        parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (position == 0) {
            holder.nameTv.setText("Ferran Montoliu");
            holder.defaultBtn.setOnClickListener(v -> {
                // TODO: POPUP ASKING IF USER WANTS TO CHANGE ITS DEFAULT ADDRESS
            });
        }

        holder.editBtn.setOnClickListener(v -> {
            // TODO: GO TO ADDRESS EDIT FRAGMENT
        });

        holder.deleteBtn.setOnClickListener(v -> {
            // TODO: POPUP ASKING IF USER IS SURE
        });
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final TextView nameTv;
        final ImageView editBtn, deleteBtn;
        final TextView defaultBtn, defaultText;

        public ViewHolder(@NonNull ItemPaymentPaypalBinding binding) {
            super(binding.getRoot());
            this.nameTv = binding.nameTv;
            this.editBtn = binding.editBtn;
            this.deleteBtn = binding.deleteBtn;
            this.defaultBtn = binding.establishAsDefaultBtn;
            this.defaultText = binding.defaultTv;
        }
    }
}
