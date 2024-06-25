package com.genealogy.view;

import com.genealogy.model.Person;
import java.util.List;

public interface FamilyTreeView {
    void showFamilyMembers(List<Person> members);
    void showChildren(List<Person> children);
    void showMessage(String message);
}

