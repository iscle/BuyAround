package cat.buyaround.app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.bumptech.glide.Glide;

import cat.buyaround.app.R;
import cat.buyaround.app.adapter.callback.IListAdapter;
import cat.buyaround.app.databinding.ItemCategoryBinding;
import cat.buyaround.app.model.Category;

public class CategoryListAdapter extends RecyclerView.Adapter<CategoryListAdapter.ViewHolder> {

    private Context mContext;
    private IListAdapter mCallback;
    private Category[] mCategories;

    public CategoryListAdapter(Context context, IListAdapter callback) {
        mContext = context;
        mCallback = callback;
        mCategories = null;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCategoryBinding binding =
                ItemCategoryBinding.inflate(LayoutInflater.from(parent.getContext()),
                        parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Category category = mCategories[position];
        holder.itemView.setOnClickListener(view -> mCallback.onItemSelected(category));
        holder.tvName.setText(category.getName());
        if (category.getIcon() != null) {
            CircularProgressDrawable circularProgressDrawable = new CircularProgressDrawable(holder.itemView.getContext());
            circularProgressDrawable.setStyle(CircularProgressDrawable.LARGE);
            circularProgressDrawable.start();

            Glide.with(mContext)
                    .asBitmap()
                    .placeholder(circularProgressDrawable)
                    .load(category.getIcon())
                    .into(holder.imgView);
        } else {
            Glide.with(mContext)
                    .load(R.drawable.ic_thumbnail)
                    .into(holder.imgView);
        }
    }

    public void setCategories(Category[] categories) {
        mCategories = categories;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mCategories != null ? mCategories.length : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgView;
        TextView tvName;

        ViewHolder(ItemCategoryBinding binding) {
            super(binding.getRoot());
            imgView = binding.itemCategoryImg;
            tvName = binding.itemCategoryName;
        }
    }
}
