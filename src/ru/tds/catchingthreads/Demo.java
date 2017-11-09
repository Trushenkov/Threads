package ru.tds.catchingthreads;

/**
 * Класс, в котором созданы и запущены два потока, демонстрирующие динамическое изменение приоритетов двух потоков.
 *
 * @author Trushenkov Dmitry 15ИТ18
 */
public class Demo {
    public static void main(String[] args) {
        FirstThread thread = new FirstThread();
        thread.start();
        SecondThread thread2 = new SecondThread();
        thread2.start();
    }
}
