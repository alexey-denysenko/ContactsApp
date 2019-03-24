package com.example.contactsapplication.common.livedata;

import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

public interface CombinerN<R> {

    void apply(@NonNull final Object[] values, @NonNull final MutableLiveData<R> result);
}
