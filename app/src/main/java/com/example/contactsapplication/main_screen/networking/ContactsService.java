package com.example.contactsapplication.main_screen.networking;

import com.example.contactsapplication.main_screen.networking.model.ContactsResponseDto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ContactsService {

    @GET("/s/F5WttwCODi1z3oo/download?path={path}&files={files}")
    Call<ContactsResponseDto> getContacts(@Query("path") String path, @Query("files") List<String> files);
}
