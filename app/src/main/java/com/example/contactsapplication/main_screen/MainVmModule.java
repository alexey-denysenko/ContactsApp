package com.example.contactsapplication.main_screen;

import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import com.example.contactsapplication.ViewModelKey;
import com.example.contactsapplication.main_screen.networking.ContactsNetworkingModule;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module (includes = ContactsNetworkingModule.class)
public interface MainVmModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel.class)
    ViewModel bindViewModel(@NonNull final MainActivityViewModel viewModel);
}
