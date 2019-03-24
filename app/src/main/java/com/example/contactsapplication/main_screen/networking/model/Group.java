
package com.example.contactsapplication.main_screen.networking.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Group {

    @SerializedName("groupName")
    @Expose
    private String groupName;

    @SerializedName("people")
    @Expose
    private List<Object> people = new ArrayList<Object>();
}
