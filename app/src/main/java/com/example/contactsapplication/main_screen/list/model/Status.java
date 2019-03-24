package com.example.contactsapplication.main_screen.list.model;

import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;

import com.example.contactsapplication.R;

public enum Status {

    AWAY("away", R.drawable.contacts_list_status_away),
    BUSY("busy", R.drawable.contacts_list_status_busy),
    OFFLINE("offline", R.drawable.contacts_list_status_offline),
    ONLINE("online", R.drawable.contacts_list_status_online),
    CALL_FORWARDING("callforwarding", R.drawable.contacts_list_call_forward),
    PENDING("pending", R.drawable.contacts_list_status_pending);

    @DrawableRes
    int icon;

    @NonNull
    final String status;

    Status(@NonNull String status, int icon) {
        this.status = status;
        this.icon = icon;
    }
}
