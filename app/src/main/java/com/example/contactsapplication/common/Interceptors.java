package com.example.contactsapplication.common;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * Qualifier for {@code OkHttp} interceptors.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Qualifier
public @interface Interceptors {
}
