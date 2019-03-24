package com.example.contactsapplication.common.networking;

import android.support.annotation.NonNull;

import timber.log.Timber;

public interface Callback<T> {

    Callback<?> IGNORE = new Callback<Object>() {
        @Override
        public void onSuccess(Object data) {

        }

        @Override
        public void onFailure(@NonNull Throwable throwable) {
            Timber.e(throwable);
        }
    };

    @NonNull
    static <T> Callback<T> ignore() {
        //noinspection unchecked
        return (Callback<T>) IGNORE;
    }

    void onSuccess(T data);

    void onFailure(@NonNull Throwable throwable);
}
