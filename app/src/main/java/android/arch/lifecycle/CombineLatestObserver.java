package android.arch.lifecycle;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.annimon.stream.Stream;
import com.example.contactsapplication.common.livedata.CombinerN;

public final class CombineLatestObserver<T, R> implements Observer<T> {

    @NonNull
    private final LiveData[] sources;
    @NonNull
    private final CombinerN<R> combiner;
    @NonNull
    private final MediatorLiveData<R> result;

    public CombineLatestObserver(@NonNull final LiveData[] sources,
                                 @NonNull final CombinerN<R> combiner,
                                 @NonNull final MediatorLiveData<R> result) {
        this.sources = sources;
        this.combiner = combiner;
        this.result = result;
    }

    @Override
    public void onChanged(@Nullable final T data) {
        final boolean hasAll = Stream.of(sources)
                .mapToInt(LiveData::getVersion)
                .allMatch(v -> v > LiveData.START_VERSION);
        if (!hasAll) {
            return;
        }
        final Object[] values = Stream.of(sources)
                .map(LiveData::getValue)
                .toArray();
        combiner.apply(values, result);
    }
}
