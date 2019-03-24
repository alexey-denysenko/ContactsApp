package com.example.contactsapplication.main_screen;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.example.contactsapplication.common.livedata.LiveDataFactory;
import com.example.contactsapplication.common.networking.Callback;
import com.example.contactsapplication.main_screen.list.model.Category;
import com.example.contactsapplication.main_screen.list.model.Contact;
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
    private final MutableLiveData<List<Object>> callbackData = new MutableLiveData<>();
    @NonNull
    private final MutableLiveData<String> searchQuery = LiveDataFactory.newDistinctMutableLiveData("");
    @NonNull
    private final MutableLiveData<Boolean> isLoading = LiveDataFactory.newDistinctMutableLiveData(FALSE);
    @NonNull
    private MutableLiveData<List<Object>> adapterSections;

    @Inject
    MainActivityViewModel(@NonNull ContactsNetworkClient networkClient) {
        isLoading.setValue(TRUE);
        networkClient.loadContacts(new ContactsCallback());
        adapterSections = LiveDataFactory.combineLatest(callbackData, searchQuery, this::filter);
    }

    @NonNull
    LiveData<List<Object>> getAdapterSections() {
        return adapterSections;
    }

    @NonNull
    LiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    void search(@NonNull final String query) {
        searchQuery.setValue(query);
    }

    void collapseCategory(int position) {

        List<Object> data = callbackData.getValue();
        List<Object> dataToRemove = new ArrayList<>();
        for(int i = position + 1; i < data.size(); i++) {
            final Object item = data.get(i);
            if(item instanceof Category) {
                break;
            }
            dataToRemove.add(item);
        }

        data.remove(dataToRemove);
        List<Object> result = new ArrayList<>(data);

        adapterSections.setValue(result);
    }

    private void filter(List<Object> callbackData, String query, MutableLiveData<List<Object>> resultLiveData) {
        if (TextUtils.isEmpty(query)) {
            resultLiveData.setValue(callbackData);
        } else {
            List<Object> result = new ArrayList<>();
            for (Object object : callbackData) {
                if (object instanceof Contact) {
                    if (((Contact) object).getFullName().contains(query)) {
                        result.add(object);
                    }
                } else if (object instanceof Category) {
                    result.add(object);
                } else {
                    throw new IllegalStateException("Unknown object type");
                }
            }
            resultLiveData.setValue(result);
        }
    }

    private final class ContactsCallback implements Callback<ContactsResponseDto> {

        @Override
        public void onSuccess(ContactsResponseDto data) {
            isLoading.setValue(FALSE);
            callbackData.setValue(ContactsMapper.map(data));
        }

        @Override
        public void onFailure(@NonNull Throwable throwable) {
            isLoading.setValue(FALSE);
            Timber.e(throwable);
        }
    }
}
