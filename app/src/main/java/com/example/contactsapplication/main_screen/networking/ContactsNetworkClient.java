package com.example.contactsapplication.main_screen.networking;

import android.support.annotation.NonNull;

import javax.inject.Inject;

public final class ContactsNetworkClient {

    @NonNull
    private final ContactsService contactsService;

    @Inject
    public ContactsNetworkClient(@NonNull ContactsService contactsService) {
        this.contactsService = contactsService;
    }


}
