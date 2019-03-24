package com.example.contactsapplication.main_screen.list;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.contactsapplication.R;
import com.example.contactsapplication.common.BaseViewHolder;
import com.example.contactsapplication.main_screen.list.model.Category;

final class VhCategory extends BaseViewHolder<Category> {

    @NonNull
    private final TextView name;
    @NonNull
    private final ImageView arrow;

    private OnCategoryClickListener listener;

    VhCategory(@NonNull final View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.category_text);
        arrow = itemView.findViewById(R.id.category_arrow);
        itemView.setOnClickListener(view -> listener.onCategoryClicked(getAdapterPosition()));
    }

    @Override
    public void bind(@NonNull final Category data) {
        if(data.isCollapsed()) {
            arrow.setImageResource(R.drawable.ic_arrow_drop_up_black_24dp);
        } else {
            arrow.setImageResource(R.drawable.ic_arrow_drop_down_black_24dp);
        }
        name.setText(data.getName());
    }

    public void setListener(@NonNull final OnCategoryClickListener listener) {
        this.listener = listener;
    }
}
