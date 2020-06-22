package cat.buyaround.app.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import cat.buyaround.app.databinding.ItemAddressBinding;

public class AddressListAdapter extends RecyclerView.Adapter<AddressListAdapter.ViewHolder> {
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemAddressBinding binding =
                ItemAddressBinding.inflate(LayoutInflater.from(parent.getContext()),
                        parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (position == 0) {
            holder.street.setText("Carrer Trompeta, 22");
            holder.city.setText("Sant Antoni de Vilamajor, 08123");
            holder.province.setText("Barcelona, Spain");
            holder.defaultBtn.setVisibility(View.GONE);
            holder.defaultText.setVisibility(View.VISIBLE);
        } else {
            holder.street.setText("Carrer Sant Joan de la Salle, 42");
            holder.city.setText("Barcelona, 08022");
            holder.province.setText("Barcelona, Spain");
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
        final TextView street;
        final TextView city;
        final TextView province;
        final ImageView editBtn;
        final ImageView deleteBtn;
        final TextView defaultBtn;
        final TextView defaultText;

        public ViewHolder(@NonNull ItemAddressBinding itemBinding) {
            super(itemBinding.getRoot());

            this.street = itemBinding.street;
            this.city = itemBinding.city;
            this.province = itemBinding.province;
            this.editBtn = itemBinding.editBtn;
            this.deleteBtn = itemBinding.deleteBtn;
            this.defaultBtn = itemBinding.establishAsDefaultBtn;
            this.defaultText = itemBinding.defaultTv;
        }
    }
}
