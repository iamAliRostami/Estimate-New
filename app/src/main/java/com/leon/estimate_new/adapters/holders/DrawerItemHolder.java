package com.leon.estimate_new.adapters.holders;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.leon.estimate_new.R;


public class DrawerItemHolder extends RecyclerView.ViewHolder {
    public final TextView textViewTitle;
    public final ImageView imageViewIcon;
    public final RelativeLayout relativeLayout;

    public DrawerItemHolder(View viewItem) {
        super(viewItem);
        this.textViewTitle = viewItem.findViewById(R.id.text_view_title);
        this.imageViewIcon = viewItem.findViewById(R.id.image_view);
        this.relativeLayout = viewItem.findViewById(R.id.relative_layout);
    }
}
