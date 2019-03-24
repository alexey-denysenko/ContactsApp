package com.example.contactsapplication.main_screen.list.model;

import android.support.annotation.NonNull;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class Contact {

    public static Contact create(@NonNull final String fullName,
                          @NonNull final String status,
                          @NonNull final String statusMessage) {
        return new AutoValue_Contact(fullName, status, statusMessage);
    }
    @NonNull
    public abstract String getFullName();
    @NonNull
    public abstract String getStatus();
    @NonNull
    public abstract String getStatusMessage();
}
