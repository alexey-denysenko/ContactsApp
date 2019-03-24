
package com.example.contactsapplication.main_screen.networking.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class GroupDto {

    @SerializedName("groupName")
    @Expose
    public String groupName;

    @SerializedName("people")
    @Expose
    public List<ContactDto> people = new ArrayList<>();
}
