
package com.example.contactsapplication.main_screen.networking.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ContactsResponseDto {

    @SerializedName("groups")
    @Expose
    private List<GroupDto> groups = new ArrayList<GroupDto>();

    public List<GroupDto> getGroups() {
        return groups;
    }

    public void setGroups(List<GroupDto> groups) {
        this.groups = groups;
    }

}
