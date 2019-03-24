package com.example.contactsapplication.main_screen.list.model;

import android.support.annotation.NonNull;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class Contact {

    static Contact create(@NonNull final String fullName,
                          @NonNull final String status,
                          @NonNull final String statusMessage) {
        return new AutoValue_Contact(fullName, status, statusMessage);
    }
    @NonNull
    abstract String getFullName();
    @NonNull
    abstract String getStatus();
    @NonNull
    abstract String getStatusMessage();
}
