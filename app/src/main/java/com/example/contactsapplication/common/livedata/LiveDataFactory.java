package com.example.contactsapplication.common.livedata;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import android.arch.lifecycle.CombineLatestObserver;

/**
 * Factory of useful {@link LiveData} implementations.
 */
public final class LiveDataFactory {

    private LiveDataFactory() {
        //no instance
    }

    @NonNull
    public static <T> MutableLiveData<T> newMutableLiveData() {
        return new MutableLiveData<>();
    }

    @NonNull
    public static <T> MutableLiveData<T> newMutableLiveData(T initialValue) {
        final MutableLiveData<T> liveData = new MutableLiveData<>();
        liveData.setValue(initialValue);
        return liveData;
    }

    /**
     * Create a new instance of {@link MutableLiveData} that doesn't emit same items.
     *
     * @param <T> type of items
     * @return an instance of {@link LiveData} that doesn't emit same items
     */
    @NonNull
    public static <T> MutableLiveData<T> newDistinctMutableLiveData() {
        return new DistinctMutableLiveData<>();
    }

    /**
     * Create a new instance of {@link MutableLiveData} that doesn't emit same items.
     *
     * @param initialValue initial value emitted by this {@link LiveData}
     * @param <T>          type of items
     * @return an instance of {@link LiveData} that doesn't emit same items
     */
    @NonNull
    public static <T> MutableLiveData<T> newDistinctMutableLiveData(T initialValue) {
        final DistinctMutableLiveData<T> liveData = new DistinctMutableLiveData<>();
        liveData.setInitialValue(initialValue);
        return liveData;
    }

    /**
     * Create a new instance of {@link MutableLiveData} that doesn't emit same items.
     *
     * @param source source {@link LiveData}
     * @param <T>    type of items
     * @return an instance of {@link LiveData} that doesn't emit same items
     */
    @NonNull
    public static <T> MediatorLiveData<T> newDistinctLiveData(@NonNull final LiveData<T> source) {
        final DistinctMediatorLiveData<T> liveData = new DistinctMediatorLiveData<>();
        liveData.addSource(source, liveData::setValue);
        return liveData;
    }

    /**
     * Create a new instance of {@link MediatorLiveData} that doesn't emit same items.
     *
     * @param <T> type of items
     * @return an instance of {@link LiveData} that doesn't emit same items
     */
    @NonNull
    public static <T> MediatorLiveData<T> newDistinctMediatorLiveData() {
        return new DistinctMediatorLiveData<>();
    }

    /**
     * Create a new instance of {@link MediatorLiveData} that doesn't emit same items.
     *
     * @param initialValue initial value emitted by this {@link LiveData}
     * @param <T>          type of items
     * @return an instance of {@link LiveData} that doesn't emit same items
     */
    @NonNull
    public static <T> MediatorLiveData<T> newDistinctMediatorLiveData(T initialValue) {
        final DistinctMediatorLiveData<T> liveData = new DistinctMediatorLiveData<>();
        liveData.setInitialValue(initialValue);
        return liveData;
    }

    private static final class DistinctMutableLiveData<T> extends MutableLiveData<T> {

        private boolean hasValue;

        @Override
        public void setValue(final T value) {
            if (!hasValue && value == null || !value.equals(getValue())) {
                hasValue = true;
                super.setValue(value);
            }
        }

        void setInitialValue(final T value) {
            super.setValue(value);
        }
    }

    private static final class DistinctMediatorLiveData<T> extends MediatorLiveData<T> {

        private boolean hasValue;

        @Override
        public void setValue(final T value) {
            if (!hasValue && value == null || !value.equals(getValue())) {
                hasValue = true;
                super.setValue(value);
            }
        }

        void setInitialValue(final T value) {
            super.setValue(value);
        }
    }

    @SuppressWarnings("unchecked")
    public static <T1, T2, R> MutableLiveData<R> combineLatest(@NonNull final LiveData<T1> ld1,
                                                        @NonNull final LiveData<T2> ld2,
                                                        @NonNull final Combiner2<T1, T2, R> combiner) {
        return combineLatest(
                new LiveData[]{ld1, ld2},
                (values, result) -> combiner.apply((T1) values[0], (T2) values[1], result)
        );
    }

    private static <R> MutableLiveData<R> combineLatest(@NonNull final LiveData[] sources,
                                                @NonNull final CombinerN<R> combiner) {
        final MediatorLiveData<R> result = new MediatorLiveData<>();

        for (final LiveData source : sources) {
            result.addSource(source, new CombineLatestObserver<>(sources, combiner, result));
        }
        return result;
    }

}