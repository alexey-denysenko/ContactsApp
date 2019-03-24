package com.example.contactsapplication.main_screen;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.contactsapplication.R;
import com.example.contactsapplication.main_screen.list.ContactsAdapter;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class MainActivity extends DaggerAppCompatActivity {

    @Inject
    ViewModelProvider.Factory factory;

    private MainActivityViewModel viewModel;
    private RecyclerView recyclerView;
    private final ContactsAdapter adapter = new ContactsAdapter();

    @NonNull
    private final Observer<List<Object>> sectionsObserver = adapter::submitList;
    @NonNull
    private final Observer<Boolean> loadingObserver = this::changeLoadingState;

    private void changeLoadingState(Boolean isLoading) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewModel = ViewModelProviders.of(this, factory).get(MainActivityViewModel.class);

        recyclerView = findViewById(R.id.recycler_view);
        initRecyclerView();
        subscribeForUpdates();
    }

    private void subscribeForUpdates() {
        viewModel.getAdapterSections().observe(this, sectionsObserver);
        viewModel.getIsLoading().observe(this, loadingObserver);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewModel.getAdapterSections().removeObserver(sectionsObserver);
        viewModel.getIsLoading().removeObserver(loadingObserver);
    }

    private void initRecyclerView() {
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
