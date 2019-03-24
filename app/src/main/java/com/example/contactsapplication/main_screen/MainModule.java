package com.example.contactsapplication.main_screen;

import com.example.contactsapplication.common.di.ActivityScope;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public interface MainModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = MainVmModule.class)
    MainActivity contributeInjector();
}
