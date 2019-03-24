package com.example.contactsapplication;

import android.app.Application;
import android.arch.lifecycle.ViewModelProvider;
import android.content.Context;
import android.support.annotation.NonNull;

import com.example.contactsapplication.common.Interceptors;
import com.example.contactsapplication.common.ViewModelFactory;

import java.util.Collections;
import java.util.Set;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.ElementsIntoSet;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

@Module
public interface ContactsAppModule {

    @Binds
    @NonNull
    Context bindContext(@NonNull final Application application);

    @Binds
    @NonNull
    ViewModelProvider.Factory bindFactory(@NonNull final ViewModelFactory factory);


}
