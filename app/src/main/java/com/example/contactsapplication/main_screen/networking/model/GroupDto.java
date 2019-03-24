
package com.example.contactsapplication.main_screen.networking.model;

import com.example.contactsapplication.main_screen.list.model.Contact;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class GroupDto {

    @SerializedName("groupName")
    @Expose
    private String groupName;

    @SerializedName("people")
    @Expose
    private List<Contact> people = new ArrayList<>();
}
