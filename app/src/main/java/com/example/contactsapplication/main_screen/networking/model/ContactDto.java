package com.example.contactsapplication.main_screen.networking.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ContactDto {

    @Expose
    @SerializedName("firstName")
    public String firstName;

    @Expose
    @SerializedName("lastName")
    public String lastName;

    @Expose
    @SerializedName("statusIcon")
    public String statusIcon;

    @Expose
    @SerializedName("statusMessage")
    public String statusMessage;
}
