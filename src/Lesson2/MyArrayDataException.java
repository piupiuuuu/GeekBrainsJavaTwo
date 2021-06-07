package Lesson2;

public class MyArrayDataException extends Exception {

    public MyArrayDataException(int i, int j) {
        super("Неправильный формат элемента массива с индексом: " + i + " " + j);
    }
}
