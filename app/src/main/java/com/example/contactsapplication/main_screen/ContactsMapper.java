package com.example.contactsapplication.main_screen;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.contactsapplication.main_screen.list.model.Category;
import com.example.contactsapplication.main_screen.list.model.Contact;
import com.example.contactsapplication.main_screen.networking.model.ContactDto;
import com.example.contactsapplication.main_screen.networking.model.ContactsResponseDto;
import com.example.contactsapplication.main_screen.networking.model.GroupDto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

final class ContactsMapper {

    static Map<Category, List<Contact>> map(@Nullable final ContactsResponseDto data) {
        if(data == null || data.groups.isEmpty()) {
            return  Collections.emptyMap();
        }

        Map<Category, List<Contact>> result = new LinkedHashMap<>();
        for(GroupDto group : data.groups) {
            Category category = Category.builder()
                    .setCollapsed(false)
                    .setName(group.groupName)
                    .build();

            ArrayList<Contact> contacts = new ArrayList<>();
            result.put(category, contacts);
            for(ContactDto contact : group.people) {
                String fullName = contact.firstName + " " + contact.lastName;
                contacts.add(Contact.create(fullName, contact.statusIcon, contact.statusMessage));
            }
        }
        return result;
    }

    static List<Object> map(@NonNull final Map<Category, List<Contact>> data) {
        if(data.isEmpty()) {
            return Collections.emptyList();
        }

        List<Object> result = new ArrayList<>();
        for(Map.Entry<Category, List<Contact>> entry : data.entrySet()) {
            result.add(entry.getKey());
            result.addAll(entry.getValue());
        }
        return result;
    }

    static List<Object> map(@NonNull final Map<Category, List<Contact>> data,
                            @NonNull final Set<String> collapsedCategories) {
        if(data.isEmpty()) {
            return Collections.emptyList();
        }

        List<Object> result = new ArrayList<>();
        for(Map.Entry<Category, List<Contact>> entry : data.entrySet()) {
            String name = entry.getKey().getName();
            Category newCategory = Category.builder()
                    .setName(name)
                    .setCollapsed(collapsedCategories.contains(name))
                    .build();
            result.add(newCategory);
            if(!collapsedCategories.contains(name)) {
                result.addAll(entry.getValue());
            }
        }
        return result;
    }
}
