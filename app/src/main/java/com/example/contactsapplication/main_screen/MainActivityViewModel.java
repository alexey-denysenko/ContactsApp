package com.example.contactsapplication.main_screen;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import com.example.contactsapplication.common.Callback;
import com.example.contactsapplication.common.LiveDataFactory;
import com.example.contactsapplication.main_screen.networking.ContactsNetworkClient;
import com.example.contactsapplication.main_screen.networking.model.ContactsResponseDto;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import timber.log.Timber;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

final class MainActivityViewModel extends ViewModel {

    @NonNull
    private final MutableLiveData<List<Object>> adapterSections = LiveDataFactory.newDistinctMutableLiveData(new ArrayList<>());
    @NonNull
    private final MutableLiveData<Boolean> isLoading = LiveDataFactory.newDistinctMutableLiveData(FALSE);

    @Inject
    MainActivityViewModel(@NonNull ContactsNetworkClient networkClient) {
        networkClient.loadContacts(new ContactsCallback());
        isLoading.setValue(TRUE);
    }

    @NonNull
    LiveData<List<Object>> getAdapterSections() {
        return adapterSections;
    }

    @NonNull
    LiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    private final class ContactsCallback implements Callback<ContactsResponseDto> {

        @Override
        public void onSuccess(ContactsResponseDto data) {
           adapterSections.setValue(ContactsMapper.map(data));
           isLoading.setValue(FALSE);
        }

        @Override
        public void onFailure(@NonNull Throwable throwable) {
            isLoading.setValue(FALSE);
            Timber.e(throwable);
        }
    }
}
