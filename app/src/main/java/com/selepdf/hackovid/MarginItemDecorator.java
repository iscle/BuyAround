package com.selepdf.hackovid;

import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import javax.inject.Inject;

public class MarginItemDecorator extends RecyclerView.ItemDecoration {
    private int spaceHeightPx;

    @Inject
    public MarginItemDecorator(DisplayMetrics displayMetrics) {
        this.spaceHeightPx = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, displayMetrics);
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        if (parent.getChildAdapterPosition(view) == 0) {
            outRect.top = spaceHeightPx;
        }
        outRect.left = spaceHeightPx;
        outRect.right = spaceHeightPx;
        outRect.bottom = spaceHeightPx;
    }
}
