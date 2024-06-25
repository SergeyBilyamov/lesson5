package com.genealogy.view;

import com.genealogy.model.Person;

import java.util.List;

public class FamilyTreeConsoleView implements FamilyTreeView {

    @Override
    public void showFamilyMembers(List<Person> members) {
        if (members == null || members.isEmpty()) {
            System.out.println("No family members found.");
            return;
        }
        System.out.println("Family members:");
        for (Person member : members) {
            System.out.println(member.getName() + " (" + member.getBirthDate() + ")");
        }
    }

    @Override
    public void showChildren(List<Person> children) {
        if (children == null || children.isEmpty()) {
            System.out.println("No children found.");
            return;
        }
        System.out.println("Children:");
        for (Person child : children) {
            System.out.println(child.getName() + " (" + child.getBirthDate() + ")");
        }
    }

    @Override
    public void showMessage(String message) {
        System.out.println(message);
    }
}