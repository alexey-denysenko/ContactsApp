package com.example.contactsapplication.main_screen;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.example.contactsapplication.R;
import com.example.contactsapplication.main_screen.list.ContactsAdapter;
import com.example.contactsapplication.main_screen.list.OnCategoryClickListener;
import com.example.contactsapplication.main_screen.list.model.Category;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public final class MainActivity extends DaggerAppCompatActivity implements OnCategoryClickListener {

    @Inject
    ViewModelProvider.Factory factory;

    private MainActivityViewModel viewModel;
    private final ContactsAdapter adapter = new ContactsAdapter();
    private ContentLoadingProgressBar progressBar;
    private RecyclerView recyclerView;
    private EditText searchField;

    @NonNull
    private final Observer<List<Object>> sectionsObserver = adapter::submitList;
    @NonNull
    private final Observer<Boolean> loadingObserver = this::changeLoadingState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewModel = ViewModelProviders.of(this, factory).get(MainActivityViewModel.class);

        getSupportActionBar().hide();
        recyclerView = findViewById(R.id.recycler_view);
        progressBar = findViewById(R.id.progress);
        searchField = findViewById(R.id.search_field);
        searchField.addTextChangedListener(textChangeListener);
        searchField.setOnEditorActionListener(this::onActionSearch);

        initRecyclerView();
        subscribeForUpdates();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewModel.getAdapterSections().removeObserver(sectionsObserver);
        viewModel.getIsLoading().removeObserver(loadingObserver);
    }

    private void subscribeForUpdates() {
        viewModel.getAdapterSections().observe(this, sectionsObserver);
        viewModel.getIsLoading().observe(this, loadingObserver);
    }

    private void changeLoadingState(Boolean isLoading) {
        if(isLoading) {
            progressBar.show();
        } else {
            progressBar.hide();
        }
    }

    private void initRecyclerView() {
        adapter.setListener(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private boolean onActionSearch(TextView textView, int actionId, KeyEvent ignored) {
        if(actionId == EditorInfo.IME_ACTION_SEARCH) {
            viewModel.search(textView.getText().toString());
        }
        return false;
    }

    @Override
    public void onCategoryClicked(Category category) {
        viewModel.collapseCategory(category);
    }

    private final TextWatcher textChangeListener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            //no-op
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            //no-op
        }

        @Override
        public void afterTextChanged(Editable editable) {
            viewModel.search(editable.toString());
        }
    };
}
