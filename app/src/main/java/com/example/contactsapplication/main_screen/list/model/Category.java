package com.example.contactsapplication.main_screen.list.model;

import android.support.annotation.NonNull;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class Category {


   abstract boolean isCollapsed();
   @NonNull
   abstract String getName();

   static Builder builder() {
      return new AutoValue_Category.Builder();
   }

   @SuppressWarnings("NullableProblems")
   @AutoValue.Builder
   abstract static class Builder {
      abstract Builder setCollapsed(boolean value);
      abstract Builder setName(@NonNull final String name);
      abstract Category build();
   }
}
