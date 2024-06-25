package com.genealogy.presenter;

import com.genealogy.model.FamilyTree;
import com.genealogy.model.Person;
import com.genealogy.view.FamilyTreeView;

import java.util.List;

public class FamilyTreePresenter {
    private FamilyTree<Person> familyTree;
    private FamilyTreeView view;

    public FamilyTreePresenter(FamilyTree<Person> familyTree, FamilyTreeView view) {
        this.familyTree = familyTree;
        this.view = view;
    }

    public void addMember(Person person) {
        familyTree.addMember(person);
    }

    public void showAllMembers() {
        List<Person> members = familyTree.getMembers();
        if (members != null && !members.isEmpty()) {
            view.showFamilyMembers(members);
        } else {
            view.showMessage("No members found in the family tree.");
        }
    }

    public void showChildren(String name) {
        List<Person> children = familyTree.getAllChildren(name);
        if (children != null && !children.isEmpty()) {
            view.showChildren(children);
        } else {
            view.showMessage(name + " has no children or is not in the family tree.");
        }
    }

    public void sortByName() {
        familyTree.sortByName();
        showAllMembers();
    }

    public void sortByBirthDate() {
        familyTree.sortByBirthDate();
        showAllMembers();
    }
}