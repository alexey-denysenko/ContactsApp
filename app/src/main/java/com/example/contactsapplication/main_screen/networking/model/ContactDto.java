package com.example.contactsapplication.main_screen.networking.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ContactDto {

    @Expose
    @SerializedName("firstName")
    String firstName;

    @Expose
    @SerializedName("lastName")
    String lastName;

    @Expose
    @SerializedName("statusIcon")
    String statusIcon;

    @Expose
    @SerializedName("statusMessage")
    String statusMessage;
}
