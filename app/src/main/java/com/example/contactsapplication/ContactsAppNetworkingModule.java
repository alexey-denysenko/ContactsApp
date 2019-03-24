package com.example.contactsapplication;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

@Module
public interface ContactsAppNetworkingModule {
    @Provides
    @NonNull
    static OkHttpClient provideOkHttp() {
        final OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder();

        if (BuildConfig.DEBUG) {
            // must be the last interceptor to catch and log modified requests
            final HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(m -> Timber.tag("NETWORK").d(m));
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            okHttpBuilder.addInterceptor(loggingInterceptor);
        }

        return okHttpBuilder.build();
    }

    @Provides
    @NonNull
    @Singleton
    static Converter.Factory provideFactory(@NonNull final Gson gson) {
        return GsonConverterFactory.create(gson);
    }

    @Provides
    @NonNull
    @Singleton
    static Gson provideGson() {
        final GsonBuilder builder = new GsonBuilder();
        return builder.create();
    }
}
