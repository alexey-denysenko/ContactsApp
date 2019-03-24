package com.example.contactsapplication.main_screen;

import com.example.contactsapplication.main_screen.list.model.Category;
import com.example.contactsapplication.main_screen.list.model.Contact;
import com.example.contactsapplication.main_screen.networking.model.ContactDto;
import com.example.contactsapplication.main_screen.networking.model.ContactsResponseDto;
import com.example.contactsapplication.main_screen.networking.model.GroupDto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ContactsMapper {

    static List<Object> map(ContactsResponseDto data) {
        if(data == null || data.groups.isEmpty()) {
            return  Collections.emptyList();
        }

        List<Object> result = new ArrayList<>();
        for(GroupDto group : data.groups) {
            Category category = Category.builder()
                    .setCollapsed(false)
                    .setName(group.groupName)
                    .build();
            result.add(category);
            for(ContactDto contact : group.people) {
                String fullName = contact.firstName + " " + contact.lastName;
                result.add(Contact.create(fullName, contact.statusIcon, contact.statusMessage));
            }
        }
        return result;
    }
}
