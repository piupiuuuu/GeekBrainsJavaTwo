package Lesson3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Написать простой класс ТелефонныйСправочник, который хранит в себе список фамилий и телефонных номеров.
 * В этот телефонный справочник с помощью метода add() можно добавлять записи. С помощью метода get() искать номер телефона
 * по фамилии. Следует учесть, что под одной фамилией может быть несколько телефонов (в случае однофамильцев), тогда при запросе
 * такой фамилии должны выводиться все телефоны.
 */

public class Phonebook {

    private final Map<String, ArrayList<String>> phoneBook = new HashMap<>();

    public Phonebook() {
    }

    public void add(String surname, String numberPhone) {
        if(phoneBook.containsKey(surname)) {
            phoneBook.get(surname).add(numberPhone);
        } else phoneBook.put(surname, new ArrayList<>(Collections.singletonList(numberPhone)));
    }

    public ArrayList<String> get(String surname) {
        if (phoneBook.containsKey(surname)) return this.phoneBook.get(surname);
        else return null;
    }

    public void printPhonebook() {
        phoneBook.forEach((a,b) -> System.out.println("Фамилия: " + a + ". Список номеров: " + b));
    }

    public static void main(String[] args) {
        Phonebook phonebook = new Phonebook();
        phonebook.add("Иванов", "+79771122334");
        phonebook.add("Иванов", "+79771122335");
        phonebook.add("Иванов", "+79771122336");
        phonebook.add("Смирнов", "+79772233445");
        phonebook.add("Петров", "+79773344556");
        phonebook.add("Ушаков", "+79774455667");
        phonebook.add("Емлютин", "+79775566778");
        phonebook.printPhonebook();
        System.out.println("Список номеров по запрошеной фамилии: " + phonebook.get("Иванов"));
        System.out.println("Список номеров по запрошеной фамилии: " + phonebook.get("Абрамов"));
    }
}
