package com.example.contactsapplication.common;

import android.support.annotation.NonNull;

import java.io.IOException;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Response;

public final class RetrofitCallback<T> implements Callback<T>, retrofit2.Callback<T> {

    @NonNull
    private final Callback<T> delegate;

    private RetrofitCallback(@NonNull Callback<T> delegate) {
        this.delegate = delegate;
    }

    @NonNull
    public static <T> RetrofitCallback<T> wrap(@NonNull final Callback<T> delegate) {
        return new RetrofitCallback<>(delegate);
    }

    @Override
    public void onSuccess(T data) {
        delegate.onSuccess(data);
    }

    @Override
    public void onFailure(@NonNull Throwable throwable) {
        delegate.onFailure(throwable);
    }

    @Override
    public void onResponse(@NonNull Call<T> call, @NonNull Response<T> response) {
        if (response.isSuccessful()) {
            delegate.onSuccess(response.body());
        } else {
            final String message = String.format(Locale.US, "Request was not successful. %d %s.",
                    response.code(), response.message());
            onFailure(call, new IOException(message));
        }
    }

    @Override
    public void onFailure(@NonNull Call<T> call, @NonNull Throwable t) {
        onFailure(t);
    }
}
