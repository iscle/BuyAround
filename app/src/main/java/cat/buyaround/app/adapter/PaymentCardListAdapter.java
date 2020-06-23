package cat.buyaround.app.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import cat.buyaround.app.databinding.ItemPaymentCardBinding;

public class PaymentCardListAdapter extends RecyclerView.Adapter<PaymentCardListAdapter.ViewHolder> {
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemPaymentCardBinding binding =
                ItemPaymentCardBinding.inflate(LayoutInflater.from(parent.getContext()),
                        parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (position == 0) {
            holder.lastDigitsTv.setText("**** 1234");
            holder.cardholderTv.setText("Ferran Montoliu");
            holder.expirationTv.setText("10/22");
            holder.defaultBtn.setVisibility(View.GONE);
            holder.defaultText.setVisibility(View.VISIBLE);
        } else {
            holder.lastDigitsTv.setText("**** 4321");
            holder.cardholderTv.setText("Iscle Gil");
            holder.expirationTv.setText("06/21");
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
        return 2;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final TextView lastDigitsTv, cardholderTv, expirationTv;
        final ImageView editBtn, deleteBtn;
        final TextView defaultBtn, defaultText;

        public ViewHolder(@NonNull ItemPaymentCardBinding binding) {
            super(binding.getRoot());
            this.lastDigitsTv = binding.lastDigitsTv;
            this.cardholderTv = binding.cardholderTv;
            this.expirationTv = binding.expirationTv;
            this.editBtn = binding.editBtn;
            this.deleteBtn = binding.deleteBtn;
            this.defaultBtn = binding.establishAsDefaultBtn;
            this.defaultText = binding.defaultTv;
        }
    }
}
