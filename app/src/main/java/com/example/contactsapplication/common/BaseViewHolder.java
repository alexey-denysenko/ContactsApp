package com.example.contactsapplication.common;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder {

    BaseViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    public abstract void bind(@NonNull final  T data);
}
