package com.example.contactsapplication.main_screen.list;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.contactsapplication.R;
import com.example.contactsapplication.common.BaseViewHolder;
import com.example.contactsapplication.main_screen.list.model.Contact;

import java.util.HashMap;
import java.util.Map;

final class VhContact extends BaseViewHolder<Contact> {

    private static final Map<String, Integer> STATUS_MAP = new HashMap<String, Integer>() {{
        put("away", R.drawable.contacts_list_status_away);
        put("busy", R.drawable.contacts_list_status_busy);
        put("offline", R.drawable.contacts_list_status_offline);
        put("online", R.drawable.contacts_list_status_online);
        put("callforwarding", R.drawable.contacts_list_call_forward);
        put("pending", R.drawable.contacts_list_status_pending);
    }};

    @NonNull
    private final ImageView statusIcon;
    @NonNull
    private final TextView fullName;
    @NonNull
    private final TextView status;
    @NonNull
    private final ImageView avatar;

    VhContact(@NonNull final View itemView) {
        super(itemView);
        statusIcon = itemView.findViewById(R.id.contact_status_icon);
        fullName = itemView.findViewById(R.id.contact_name);
        status = itemView.findViewById(R.id.contact_status);
        avatar = itemView.findViewById(R.id.contact_avatar);
    }

    @Override
    public void bind(@NonNull final Contact data) {
        statusIcon.setImageResource(STATUS_MAP.get(data.getStatus()));
        fullName.setText(data.getFullName());
        status.setText(data.getStatus());
        avatar.setImageResource(R.drawable.contacts_list_avatar_male);
    }
}
