package com.example.contactsapplication.main_screen.networking;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.contactsapplication.R;
import com.example.contactsapplication.common.ServiceFactory;

import dagger.Module;
import dagger.Provides;

@Module
public interface ContactsNetworkingModule {

    @Provides
    static ContactsService provideContactsService(@NonNull final Context context,
                                                  @NonNull final ServiceFactory serviceFactory) {

        String baseUrl = context.getString(R.string.base_url);
        return serviceFactory.create(baseUrl, ContactsService.class);
    }
}
