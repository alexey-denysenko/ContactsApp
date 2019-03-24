package com.example.contactsapplication;

import android.support.annotation.NonNull;

import com.example.contactsapplication.common.Interceptors;

import java.util.Collections;
import java.util.Set;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.ElementsIntoSet;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

@Module
public interface ContactsAppNetworkingModule {
    @Provides
    @NonNull
    static OkHttpClient provideOkHttp(@Interceptors @NonNull final Set<Interceptor> interceptors) {
        final OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder();

        for (Interceptor interceptor : interceptors) {
            okHttpBuilder.addInterceptor(interceptor);
        }
        return okHttpBuilder.build();

    }

    @Provides
    @ElementsIntoSet
    @Interceptors
    @NonNull
    static Set<Interceptor> provideOkHttpInterceptors(@NonNull final HttpLoggingInterceptor httpLoggingInterceptor) {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return Collections.singleton(loggingInterceptor);
    }
}
