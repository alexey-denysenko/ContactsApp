package com.example.contactsapplication.common.livedata;

import android.arch.lifecycle.MutableLiveData;

public interface Combiner2<T1, T2, R> {

    void apply(T1 t1, T2 t2, MutableLiveData<R> data);
}
