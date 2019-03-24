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
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import javax.inject.Inject;

import timber.log.Timber;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

final class MainActivityViewModel extends ViewModel {

    @NonNull
    private final MutableLiveData<Map<Category, List<Contact>>> callbackData = new MutableLiveData<>();
    @NonNull
    private final MutableLiveData<String> searchQuery = LiveDataFactory.newDistinctMutableLiveData("");
    @NonNull
    private final MutableLiveData<Boolean> isLoading = LiveDataFactory.newDistinctMutableLiveData(FALSE);
    @NonNull
    private MutableLiveData<List<Object>> adapterSections;
    private Set<String> collapsedCategories = new HashSet<>();

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

    void collapseCategory(Category category) {

        String name = category.getName();
        if(collapsedCategories.contains(name)) {
            collapsedCategories.remove(name);
        } else {
            collapsedCategories.add(name);
        }

        List<Object> result = ContactsMapper.map(Objects.requireNonNull(callbackData.getValue()), collapsedCategories);
        adapterSections.setValue(result);
    }

    private void filter(Map<Category, List<Contact>> callbackData, String query, MutableLiveData<List<Object>> resultLiveData) {
        if (TextUtils.isEmpty(query)) {
            resultLiveData.setValue(ContactsMapper.map(callbackData));
        } else {
            List<Object> result = new ArrayList<>();
            for (Map.Entry<Category, List<Contact>> entry : callbackData.entrySet()) {
                result.add(entry.getKey());
                List<Contact> contacts = entry.getValue();
                for (Contact contact : contacts) {
                    if (contact.getFullName().toLowerCase().contains(query.toLowerCase())) {
                        result.add(contact);
                    }
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
