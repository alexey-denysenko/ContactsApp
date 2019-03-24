package com.example.contactsapplication.main_screen;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainContributeInjectorModule {

    @ContributesAndroidInjector(modules = MainModule.class)
    abstract MainActivity contributeInjector();
}
