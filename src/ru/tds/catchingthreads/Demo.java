package ru.tds.catchingthreads;

/**
 * Класс, в котором созданы и запущены два потока, демонстрирующие динамическое изменение приоритетов двух потоков.
 *
 * @author Trushenkov Dmitry 15ИТ18
 */
public class Demo {
    public static void main(String[] args) {
        new FirstThread().start();
        new SecondThread().start();
    }
}
