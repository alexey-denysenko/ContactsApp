package com.example.contactsapplication;

import android.app.Application;
import android.arch.lifecycle.ViewModelProvider;
import android.content.Context;
import android.support.annotation.NonNull;

import com.example.contactsapplication.common.di.ViewModelFactory;

import dagger.Binds;
import dagger.Module;

@Module
public interface ContactsAppModule {

    @Binds
    @NonNull
    Context bindContext(@NonNull final Application application);

    @Binds
    @NonNull
    ViewModelProvider.Factory bindFactory(@NonNull final ViewModelFactory factory);


}
