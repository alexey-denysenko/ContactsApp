package com.example.contactsapplication.main_screen.list.model;

import android.support.annotation.NonNull;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class Category {

   public abstract boolean isCollapsed();
   @NonNull
   public abstract String getName();

   public static Builder builder() {
      return new AutoValue_Category.Builder();
   }

   @SuppressWarnings("NullableProblems")
   @AutoValue.Builder
   public abstract static class Builder {
      public abstract Builder setCollapsed(boolean value);
      public abstract Builder setName(@NonNull final String name);
      public abstract Category build();
   }
}
