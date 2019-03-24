package com.example.contactsapplication;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import dagger.Binds;
import dagger.Module;

@Module
public interface ContactsAppModule {

    @Binds
    @NonNull
    Context bindContext(@NonNull final Application application);
}
