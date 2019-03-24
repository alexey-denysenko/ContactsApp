package com.example.contactsapplication;


import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;

public class ContactsApplication extends DaggerApplication {
    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerContactsAppComponent.builder()
                .withApplication(this)
                .build();
    }
}
