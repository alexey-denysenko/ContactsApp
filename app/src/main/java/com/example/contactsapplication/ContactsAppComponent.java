package com.example.contactsapplication;

import android.app.Application;
import android.support.annotation.NonNull;

import com.example.contactsapplication.main_screen.MainModule;
import com.example.contactsapplication.main_screen.MainVmModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,

        ContactsAppModule.class,
        ContactsAppNetworkingModule.class,

        MainModule.class,
        MainVmModule.class
})
public interface ContactsAppComponent extends AndroidInjector<ContactsApplication> {

    @SuppressWarnings("NullableProblems")
    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder withApplication(@NonNull final Application application);

        ContactsAppComponent build();
    }
}

