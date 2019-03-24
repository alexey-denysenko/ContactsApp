package com.example.contactsapplication.common.di;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Activity scope.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Scope
public @interface ActivityScope {
}
