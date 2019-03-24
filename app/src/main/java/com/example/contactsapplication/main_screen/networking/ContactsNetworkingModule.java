package com.example.contactsapplication.main_screen.networking;

import android.content.Context;

import com.example.contactsapplication.R;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public interface ContactsNetworkingModule {

    @Provides
    static ContactsService provideContactsService(Context context) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(context.getString(R.string.base_url))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(ContactsService.class);
    }
}
