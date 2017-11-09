package ru.tds.interferens;

/**
 * Класс для запуска метода example() из класса InterferenceExample, демонстрирующий инкремент переменной i 2000 раз без потери данных.
 *
 * @author Trushenkov Dmitry 15ИТ18
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        new InterferenceExample().example();
    }
}
