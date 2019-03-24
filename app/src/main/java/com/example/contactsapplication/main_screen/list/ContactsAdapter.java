package com.example.contactsapplication.main_screen.list;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.contactsapplication.R;
import com.example.contactsapplication.common.BaseAdapter;
import com.example.contactsapplication.common.BaseViewHolder;
import com.example.contactsapplication.main_screen.list.model.Category;
import com.example.contactsapplication.main_screen.list.model.Contact;

public class ContactsAdapter extends BaseAdapter<Object, BaseViewHolder> {

    private final int CONTACT_TYPE = R.layout.item_contact;
    private final int CATEGORY_TYPE = R.layout.item_category;

    public ContactsAdapter() {
        super(new ContactsDiffCallback());
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        if(viewType == CONTACT_TYPE) {
            return new VhContact(view);
        } else if (viewType == CATEGORY_TYPE) {
            return new VhCategory(view);
        } else {
            throw new IllegalArgumentException(String.format("Unknown view type: %d.", viewType));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    @Override
    public int getItemViewType(int position) {
        final Object item = getItem(position);
        if (item instanceof Contact) {
            return CONTACT_TYPE;
        } else if (item instanceof Category) {
            return CATEGORY_TYPE;
        } else {
            throw new IllegalArgumentException(String.format("Unknown item: %s.", item));
        }
    }
}
