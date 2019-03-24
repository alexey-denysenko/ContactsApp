package com.example.contactsapplication.main_screen;

import android.arch.lifecycle.ViewModel;
import android.content.Context;

import com.example.contactsapplication.R;
import com.example.contactsapplication.ViewModelKey;
import com.example.contactsapplication.main_screen.networking.ContactsService;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public abstract class MainModule {

    @Provides
    @Singleton
    static ContactsService provideContactsService(Context context) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(context.getString(R.string.base_url))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(ContactsService.class);
    }

    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel.class)
    abstract ViewModel bindMainViewModel(MainActivityViewModel viewModel);
}
