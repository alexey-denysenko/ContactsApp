package com.example.contactsapplication.common;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.recyclerview.extensions.AsyncListDiffer;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;

import java.util.List;

public abstract class BaseAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {


    @NonNull
    private AsyncListDiffer<T> differ;


    public BaseAdapter(@NonNull final DiffUtil.ItemCallback<T> diffCallback) {
        differ = new AsyncListDiffer<>(this, diffCallback);
    }

    @NonNull
    public AsyncListDiffer<T> getDiffer() {
        return differ;
    }

    public void submitList(@NonNull List<T> list) {
        differ.submitList(list);
    }

    @NonNull
    public T getItem(int position) {
        //noinspection unchecked
        return differ.getCurrentList().get(position);
    }

    @Override
    public int getItemCount() {
        return differ.getCurrentList().size();
    }

    public int getPosition(@Nullable final T item) {
        return differ.getCurrentList().indexOf(item);
    }
}
