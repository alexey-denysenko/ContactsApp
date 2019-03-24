package com.example.contactsapplication.main_screen.list;

import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.text.TextUtils;

import com.example.contactsapplication.main_screen.list.model.Category;
import com.example.contactsapplication.main_screen.list.model.Contact;

final class ContactsDiffCallback extends DiffUtil.ItemCallback<Object> {

    @Override
    public boolean areItemsTheSame(@NonNull Object oldItem, @NonNull Object newItem) {
        if(oldItem instanceof Contact && newItem instanceof Contact) {
            final Contact oldContact = (Contact) oldItem;
            final Contact newContact = (Contact) newItem;
            //assume that contacts the same, when names are the same
            return TextUtils.equals(oldContact.getFullName(), newContact.getFullName());
        } else if (oldItem instanceof Category && newItem instanceof Category) {
            final Category oldCategory = (Category) oldItem;
            final Category newCategory = (Category) newItem;
            //assume that contacts the same, when names are the same
            return TextUtils.equals(oldCategory.getName(), newCategory.getName());
        }
        return oldItem.equals(newItem);
    }

    @Override
    public boolean areContentsTheSame(@NonNull Object oldItem, @NonNull Object newItem) {
        return oldItem.equals(newItem);
    }

    @Override
    public Object getChangePayload(@NonNull Object oldItem, @NonNull Object newItem) {
        return "";
    }
}
