package com.genealogy;

import com.genealogy.io.FamilyTreeFileHandler;
import com.genealogy.io.FamilyTreeFileHandlerImpl;
import com.genealogy.model.FamilyTree;
import com.genealogy.model.Gender;
import com.genealogy.model.Person;
import com.genealogy.presenter.FamilyTreePresenter;
import com.genealogy.view.FamilyTreeConsoleView;
import com.genealogy.view.FamilyTreeView;

import java.io.IOException;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        // Создание членов семьи
        Person john = new Person("John Doe", Gender.MALE, LocalDate.of(1980, 1, 15));
        Person jane = new Person("Jane Doe", Gender.FEMALE, LocalDate.of(1982, 5, 22));
        Person child1 = new Person("Alice Doe", Gender.FEMALE, LocalDate.of(2005, 7, 30));
        Person child2 = new Person("Bob Doe", Gender.MALE, LocalDate.of(2008, 11, 5));

        // Добавление даты смерти
        john.setDeathDate(LocalDate.of(2020, 4, 10)); // Джон умер в 2020 году

        // Добавление детей к родителям
        john.addChild(child1);
        john.addChild(child2);
        jane.addChild(child1);
        jane.addChild(child2);

        // Создание генеалогического древа и добавление членов семьи
        FamilyTree<Person> familyTree = new FamilyTree<>();
        familyTree.addMember(john);
        familyTree.addMember(jane);
        familyTree.addMember(child1);
        familyTree.addMember(child2);

        // Создание представления и презентера
        FamilyTreeView view = new FamilyTreeConsoleView();
        FamilyTreePresenter presenter = new FamilyTreePresenter(familyTree, view);

        // Сортировка по имени и вывод
        presenter.sortByName();

        // Сортировка по дате рождения и вывод
        presenter.sortByBirthDate();

        // Проведение исследования: получение всех детей выбранного человека
        presenter.showChildren("John Doe");

        // Сохранение генеалогического древа в файл
        FamilyTreeFileHandler<Person> fileHandler = new FamilyTreeFileHandlerImpl<>();
        String filename = "family_tree.ser";
        try {
            fileHandler.saveToFile(familyTree, filename);
            view.showMessage("Family tree saved to file.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Загрузка генеалогического древа из файла
        try {
            FamilyTree<Person> loadedFamilyTree = fileHandler.loadFromFile(filename);
            FamilyTreePresenter loadedPresenter = new FamilyTreePresenter(loadedFamilyTree, view);
            view.showMessage("Family tree loaded from file.");

            // Проведение исследования: получение всех детей выбранного человека
            loadedPresenter.showChildren("John Doe");

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}