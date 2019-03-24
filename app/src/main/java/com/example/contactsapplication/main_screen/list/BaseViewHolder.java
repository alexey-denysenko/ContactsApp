package com.example.contactsapplication.main_screen.list;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder {

    BaseViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    abstract void bind(@NonNull final  T data);
}
