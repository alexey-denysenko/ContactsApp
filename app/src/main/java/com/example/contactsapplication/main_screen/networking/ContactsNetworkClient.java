package com.example.contactsapplication.main_screen.networking;

import android.support.annotation.NonNull;

import com.example.contactsapplication.common.Callback;
import com.example.contactsapplication.common.RetrofitCallback;
import com.example.contactsapplication.main_screen.networking.model.ContactsResponseDto;

import java.util.Collections;

import javax.inject.Inject;

public final class ContactsNetworkClient {

    @NonNull
    private final ContactsService contactsService;

    @Inject
    ContactsNetworkClient(@NonNull ContactsService contactsService) {
        this.contactsService = contactsService;
    }

    public void loadContacts(Callback<ContactsResponseDto> callback) {
        RetrofitCallback<ContactsResponseDto> retrofitCallback = RetrofitCallback.wrap(callback);
        contactsService.getContacts("/", Collections.singletonList("contacts.json")).enqueue(retrofitCallback);
    }
}
